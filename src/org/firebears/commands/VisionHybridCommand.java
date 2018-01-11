package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Do rotation and strafing at the same time to align up to the target.
 */
public class VisionHybridCommand extends PIDCommand {

	double moveDistance;
	double startDistance;
	double targetDistance;
	double timeout;
	protected final double SPEED = 0.25;
	protected final double tolerance = 0.25;
	
	double startAngle;
	double currentAngle;
	
    public VisionHybridCommand() {
    	super(.025, 0.0, 0.0);
    	requires(Robot.chassis);
    	
    	getPIDController().setAbsoluteTolerance(tolerance);
    }
    
    public double toInches(double EncoderValue){
    	return EncoderValue/26.5;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	moveDistance = -1.0 * (Robot.vision.getDistance() * Math.sin(Robot.vision.getAngle() * Math.PI / 180));
    	timeout = System.currentTimeMillis() + 1000 * 5;
    	startDistance = toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX));
    	targetDistance = startDistance - moveDistance;
    	getPIDController().setSetpoint(targetDistance);
    	
    	startAngle = RobotMap.navXBoard.getAngle() + ( Robot.vision.getTilt() * 4 );
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	if (Math.abs(toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX)) - targetDistance) < tolerance) {
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
		return toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX));
	}

    public double getAngleDifference(){
    	return currentAngle - startAngle;
    }
	
	@Override
	protected void usePIDOutput(double output) {
    	currentAngle = RobotMap.navXBoard.getAngle();
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(0.0, output, getAngleDifference());
	}
	
}
