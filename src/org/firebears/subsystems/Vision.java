package org.firebears.subsystems;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This subsystem is for gathering information from the raspberry pi about where the target is.
 */
public class Vision extends Subsystem {

	Receiver2 udp_receiver;
	Thread udp_receiver_thread;
	long lastGoodObservation = System.currentTimeMillis();
	long lightRingTimeout = 0;
	int[] lastFiveConfidence = new int[5];
	int nextConfidence = 0;
	
	public Vision() {
		// Gather Information from Raspberry Pi on a separate thread.
		udp_receiver = new Receiver2(5810);
    	udp_receiver_thread = new Thread(udp_receiver);
    	udp_receiver_thread.setDaemon(true);
    	udp_receiver_thread.start();
	}

    public void initDefaultCommand() {
    }

    /*
     * Get the angle based on the target's offset from the center ( in degrees ).
     */
    public float getAngle(){
    	//return udp_receiver.angle * -1f;//Upside down test robot camera
    			
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
    	double averageConfidence;
    	double confidenceSum = 0;
    	for (int i = 0; i < lastFiveConfidence.length; i++){
    		confidenceSum += lastFiveConfidence[i];
    	}
    	averageConfidence = confidenceSum / lastFiveConfidence.length;
    	return averageConfidence > 0.5;
//    	return millisecondsSinceLastGoodVision() < 5 * 1000L;
    }
    
    public long millisecondsSinceLastGoodVision() {
    	return System.currentTimeMillis() - lastGoodObservation;
    }
    
    public void setLightRingOn() {
    	lightRingTimeout = 0;
    	RobotMap.gearLightRing.set(Relay.Value.kForward);
    }
    
    public void setLightRingOff() {
    	lightRingTimeout = System.currentTimeMillis() + 3 * 1000L;
    }
    
    public boolean isLightRingOn() {
    	if(RobotMap.gearLightRing ==null)
    		return false;
    	else
    	return RobotMap.gearLightRing.get() != Relay.Value.kOff;	
    }
    
    public void update() {
    	if (lightRingTimeout > 0 && System.currentTimeMillis() > lightRingTimeout)  {
    		RobotMap.gearLightRing.set(Relay.Value.kOff);
    		lightRingTimeout = 0;
    	}
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
    				confidence = info4;
    				lastFiveConfidence[nextConfidence] = info4;
    				nextConfidence = (nextConfidence + 1) % lastFiveConfidence.length;
					if (confidence == 1) {
						angle = info1;
						distance = info2;
						tilt = info3;
						lastGoodObservation = System.currentTimeMillis();
					}
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

