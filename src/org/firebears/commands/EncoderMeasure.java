package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderMeasure extends Command {

	int startPosFrontLeft;
	int startPosFrontRight;
	int startPosBackLeft;
	int startPosBackRight;
	
    public EncoderMeasure() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startPosFrontLeft = RobotMap.chassisfrontLeft.getEncPosition();
    	startPosFrontRight = RobotMap.chassisfrontRight.getEncPosition();
    	startPosBackLeft = RobotMap.chassisrearLeft.getEncPosition();
    	startPosBackRight = RobotMap.chassisrearRight.getEncPosition();
    	SmartDashboard.putNumber("EfrontLeft Startdist", startPosFrontLeft);
		SmartDashboard.putNumber("EfrontRight Startdist", startPosFrontRight);
		SmartDashboard.putNumber("ErearLeft Startdist", startPosBackLeft);
		SmartDashboard.putNumber("ErearRight Startdist", startPosBackRight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.drive(0.0, -0.25, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.chassisfrontLeft.getEncPosition() <= startPosFrontLeft - 1000;
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
}
