package org.firebears.commands;

import org.firebears.*;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionAngleAdjustCommand extends PIDCommand {

	float turnValue;
	float driveValue;
	long timeout;
	float targetAngle;
	protected final double SPEED = 0.25;
	protected double angleTolerance = 10.0;
	
    public VisionAngleAdjustCommand() {
    	//PID Values not correct
    	super(0.1, 0.0, 0.0);
        requires(Robot.chassis);
//        driveValue = (float)distance;
        
		getPIDController().setAbsoluteTolerance(angleTolerance);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
//    	RobotMap.gearLightRing.set(Relay.Value.kForward);
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	turnValue = Robot.vision.getAngle();
    	targetAngle = 0;
    	getPIDController().setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	double difference = getAngleDifference();
//    	SmartDashboard.putString("End:", "" + difference);
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	
    	if (Robot.vision.getAngle() < angleTolerance){
//    		RobotMap.gearLightRing.set(Relay.Value.kOff);
    		return true;
    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
//    	RobotMap.gearLightRing.set(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return Robot.vision.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(0.0, output, 0.0);
	}
	
	@Override
    public String toString() {
    	return "VisionAngleCommand(" + targetAngle + ")";
    }
}