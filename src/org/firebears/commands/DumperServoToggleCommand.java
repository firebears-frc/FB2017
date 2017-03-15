package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Test command to toggle whether the servo is up or down.
 */
public class DumperServoToggleCommand extends Command {
	
	int servoMode = 2;
	final int RAISE_SERVO = 1;
	final int LOWER_SERVO = 2;
	
    public DumperServoToggleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.hopper);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (servoMode == RAISE_SERVO){
    		Robot.hopper.raiseDumperServo();
    		servoMode = 2;
    	}else if (servoMode == LOWER_SERVO){
    		Robot.hopper.lowerDumperServo();
    		servoMode = 1;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
