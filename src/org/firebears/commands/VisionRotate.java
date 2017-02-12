package org.firebears.commands;

import org.firebears.*;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionRotate extends PIDCommand {

	float turnValue;
	float driveValue;
	long timeout;
	float targetAngle;
	protected final double SPEED = 0.25;
	protected double angleTolerance = 1.5;
	
	private boolean useTilt = false;
	
    public VisionRotate(boolean p_useTilt) {
    	//PID Values not correct
    	super(0.066, 0.0, 0.0);
        requires(Robot.chassis);
//        driveValue = (float)distance;
        
        getPIDController().setContinuous(true);
		getPIDController().setInputRange(-180.0, 180.0);
		getPIDController().setAbsoluteTolerance(angleTolerance);
		
		useTilt = p_useTilt;
    }
    
    private double getAngleDifference() {
		return bound((float)RobotMap.navXBoard.getAngle() - targetAngle);
	}

    public static float bound(float angle) {
		while (angle > 180) angle -= 360;
		while (angle < -180) angle += 360;
		return angle;
	}
    
    // Called just before this Command runs the first time
    protected void initialize() {
//    	RobotMap.gearLightRing.set(Relay.Value.kForward);
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	if(useTilt) {
    		turnValue = Robot.vision.getTilt() * 4;
    	}else{
    		turnValue = Robot.vision.getAngle();
    	}
    	targetAngle = bound((float)RobotMap.navXBoard.getAngle() - turnValue);
    	getPIDController().setSetpoint(0);
    	SmartDashboard.putNumber("VisionTarget:", targetAngle);
    	
    	System.out.println("Vision Rotate Start At " + getAngleDifference() + " degrees off");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double difference = getAngleDifference();
    	SmartDashboard.putNumber("Angle Difference:", difference);
//    	SmartDashboard.putString("End:", "" + difference);
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	
    	if (Math.abs(difference) < angleTolerance){
//    		RobotMap.gearLightRing.set(Relay.Value.kOff);
    		return true;
    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
//    	RobotMap.gearLightRing.set(Relay.Value.kOff);
    	System.out.println("Vision Rotate End At " + getAngleDifference() + " degrees off");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return getAngleDifference();
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(0.0, 0.0, output);
	}
	
	@Override
    public String toString() {
    	return "RotationCommand(" + targetAngle + ")";
    }
}
