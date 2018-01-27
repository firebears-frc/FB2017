package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Move the robot forward x inches at a specific speed or .25 if no speed is specified.
 */
public class NewVisionDriveCommand extends PIDCommand {

	double moveDistance;
	double startDistance;
	double targetDistance;
	double timeout;
	protected final double SPEED = .225;
	protected final double tolerance = 0.25;
	final double ENCODER_RATIO = 30;//34.75;
	
    public NewVisionDriveCommand() {
    	super(.025, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setAbsoluteTolerance(tolerance);
    	
    	
    }
    
    public double toInches(double EncoderValue){
//    	 return (EncoderValue/ENCODER_RATIO) * (4 * Math.PI);
    	return EncoderValue/ENCODER_RATIO;//20
    }
    
    public double toEncoderValue(double inches) {
    	return inches * ENCODER_RATIO;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	startDistance = toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX));
    	moveDistance = Robot.testvision.getDistance();
    	targetDistance = startDistance + moveDistance - 4;
    	getPIDController().setSetpoint(targetDistance);
    	
    	SmartDashboard.putNumber("Start", startDistance);
    	SmartDashboard.putNumber("target", targetDistance);
    	SmartDashboard.putNumber("Distance", moveDistance);
    	
    	System.out.println("Vision Forward Move " + moveDistance + "inches, Start At " + (toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX)) - targetDistance) + " degrees off");
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
    	double currentPos = toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX));
    	SmartDashboard.putNumber("Current", currentPos);
    	if (Math.abs(currentPos - targetDistance) < tolerance) {
    		System.out.println("Vision Forward: Within Tolerance");
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
    	System.out.println("Vision Forward Start At " + (toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX)) - targetDistance) + " degrees off");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return toInches(RobotMap.chassisfrontLeft.getSelectedSensorPosition(RobotMap.PID_IDX));
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.min(-SPEED, Math.max(output, SPEED));
		Robot.chassis.drive(0.0, output, 0.0);
	}
}
