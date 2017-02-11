package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class MoveForwardInches extends PIDCommand {

	double moveDistance;
	double startDistance;
	double targetDistance;
	double timeout;
	protected final double SPEED = 0.25;
	protected final double tolerance = 0.25;
	
    public MoveForwardInches(double inches) {
    	super(.075, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setAbsoluteTolerance(tolerance);
    	
    	moveDistance = inches;
    }
    
    public double toInches(double EncoderValue){
    	return EncoderValue/26.5;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 5;
    	startDistance = toInches(RobotMap.chassisfrontLeft.getEncPosition());
    	targetDistance = startDistance - moveDistance;
    	getPIDController().setSetpoint(targetDistance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	if (toInches(RobotMap.chassisfrontLeft.getEncPosition()) - targetDistance < tolerance){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return toInches(RobotMap.chassisfrontLeft.getEncPosition());
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(0.0, output, 0.0);
	}
}
