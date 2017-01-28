package org.firebears.commands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionCommand extends Command{

	static Receiver2 udp_receiver;
	static Thread udp_receiver_thread;
	
    public VisionCommand() {
        requires(Robot.vision);
    	udp_receiver = new Receiver2(5810);
    	udp_receiver_thread = new Thread(udp_receiver);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	udp_receiver_thread.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private class Receiver2 implements Runnable {
    	
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
    
//    public void main2() {
//    	udp_receiver = new Receiver2(5810);
//    	udp_receiver_thread = new Thread(udp_receiver);
//    	udp_receiver_thread.start();
//    }
//    
//    public static void main(String args[]){
//    	 VisionCommand vision = new VisionCommand();
//    	 vision.main2();
//    }
}
