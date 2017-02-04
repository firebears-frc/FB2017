package org.firebears.commands;

import org.firebears.*;
import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoRotate extends PIDCommand {

	float turnValue;
	float driveValue;
	long timeout;
	float targetAngle;
	protected final double SPEED = 0.5;
	protected double angleTolerance = 1.0;
	
    public AutoRotate(double angle) {
    	super(.0066, 0.0, 0.0);
        requires(Robot.chassis);
        turnValue = (float)angle;
//        driveValue = (float)distance;
        
        getPIDController().setContinuous(true);
		getPIDController().setInputRange(-180.0, 180.0);
		getPIDController().setAbsoluteTolerance(angleTolerance);
    }
    
    private double getAngleDifference() {
		return (float)RobotMap.navXBoard.getAngle() - targetAngle;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	targetAngle = (float)RobotMap.navXBoard.getAngle() + turnValue;
    	getPIDController().setSetpoint(0.0);
    	SmartDashboard.putString("Target:", "RotationCommand(" + targetAngle + ")");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double difference = getAngleDifference();
    	return Math.abs(difference) < angleTolerance ||System.currentTimeMillis() >= timeout;
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
