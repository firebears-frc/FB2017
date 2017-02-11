package org.firebears.subsystems;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This subsystem is for gathering information from the raspberry pi about where the target is.
 */
public class Vision extends Subsystem {

	Receiver2 udp_receiver;
	Thread udp_receiver_thread;
	
	public Vision() {
		// Gather Information from Raspberry Pi on a separate thread.
		udp_receiver = new Receiver2(5810);
    	udp_receiver_thread = new Thread(udp_receiver);
    	udp_receiver_thread.start();
	}

    public void initDefaultCommand() {
    }

    /*
     * Get the angle based on the target's offset from the center ( in degrees ).
     */
    public float getAngle(){
    	return udp_receiver.angle;
    }
    
    /*
     * Get the distance ( in inches ) to the target.
     */
    public float getDistance(){
    	return udp_receiver.distance;
    }
    
    /*
     * Get the angle based on the difference in size of the two sides of the target ( in pixels ).
     */
    public float getTilt(){
    	return udp_receiver.tilt;
    }
    
    /*
     * Returns true if camera can see target, returns false if it is off camera.
     */
    public boolean isTargetVisible() {
    	return udp_receiver.confidence == 1;
    }
    
    /*
     * The thread that communicates with the raspberry pi.
     */
    public class Receiver2 implements Runnable {
    	
    	final SocketAddress address;
    	float angle;
    	float distance;
    	float tilt;
    	int confidence;
    	
    	public Receiver2(int port) {
    		address = new InetSocketAddress(port);
    	}

    	public void run() {
    		float info1;
    		float info2;
    		float info3;
    		int info4;
    		try (DatagramChannel channel = DatagramChannel.open().bind(address)) {
    			ByteBuffer buffer = ByteBuffer.allocate(512);
    			while (true) {
    				buffer.clear();
    				channel.receive(buffer);
    				buffer.flip();
    				info1 = buffer.getFloat();
    				info2 = buffer.getFloat();
    				info3 = buffer.getFloat();
    				info4 = buffer.getInt();
    				angle = info1;
    				distance = info2;
    				tilt = info3;
    				confidence = info4;
    				String receivedmessage = "Received a message: "+ info1 + " " + info2 + " " + info3 + " " + info4;
    				SmartDashboard.putNumber("Vision Angle:", info1);
    				SmartDashboard.putNumber("Vision Distance:", info2);
    				SmartDashboard.putNumber("Vision Tilt:", info3);
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    }

}

