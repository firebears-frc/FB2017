package org.firebears.subsystems;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {

	Receiver2 udp_receiver;
	Thread udp_receiver_thread;
	
	public Vision(){
		udp_receiver = new Receiver2(5810);
    	udp_receiver_thread = new Thread(udp_receiver);
    	udp_receiver_thread.start();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public float getAngle(){
//    	float[] angles = new float[5];
//    	
//    	for (int i = 0; i < 6; i++){
//    		angles[i] = udp_receiver.angle;
//    		System.out.print(angles[i]);
//    		
//    	}
    	
//    	float angle1 = udp_receiver.angle;
//    	float angle2 = udp_receiver.angle;
//    	float angle3 = udp_receiver.angle;
//    	float angle4 = udp_receiver.angle;
//    	float angle5 = udp_receiver.angle;
    	return udp_receiver.angle;
//    	return 5.0f;
    }
    
    public float getDistance(){
    	return udp_receiver.distance;
    }
    
    public float getTilt(){
    	return udp_receiver.tilt;
    }
    
    public int getConfidence(){
    	return udp_receiver.confidence;
    }
    
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
    				SmartDashboard.putString("Info:", receivedmessage);
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    }
}

