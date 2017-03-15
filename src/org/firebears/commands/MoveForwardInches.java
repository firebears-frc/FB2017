package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class MoveForwardInches extends PIDCommand {

	double moveDistance;
	double startDistance;
	double targetDistance;
	double timeout;
	protected final double SPEED;
	protected final double tolerance = 0.25;
	
    public MoveForwardInches(double inches, double speed) {
    	super(.025, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setAbsoluteTolerance(tolerance);
    	
    	SPEED = speed;
    	moveDistance = inches;
    }
    
    public MoveForwardInches(double inces) {
    	this(inces, .25);
    }
    
    public double toInches(double EncoderValue){
    	return EncoderValue/40;//26.5
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 5;
    	startDistance = toInches(RobotMap.chassisfrontLeft.getEncPosition());
    	targetDistance = startDistance - moveDistance;
    	getPIDController().setSetpoint(targetDistance);
    	
    	System.out.println("Vision Forward Move " + moveDistance + "inches, Start At " + (toInches(RobotMap.chassisfrontLeft.getEncPosition()) - targetDistance) + " degrees off");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		System.out.println("Vision Forward: Timeout");
    		return true;
    	}
    	if (Math.abs(toInches(RobotMap.chassisfrontLeft.getEncPosition()) - targetDistance) < tolerance) {
    		System.out.println("Vision Forward: Within Tolerance");
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
    	System.out.println("Vision Forward Start At " + (toInches(RobotMap.chassisfrontLeft.getEncPosition()) - targetDistance) + " degrees off");
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
