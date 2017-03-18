package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Wait x seconds for Vision. If vision is connected or time expires the command ends.
 */
public class WaitFor_FindVisionCommand extends Command {
	
	long timeout;

    public WaitFor_FindVisionCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setLightRingOn();
    	timeout = System.currentTimeMillis() + 1000 * 5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (System.currentTimeMillis() >= timeout){
        	return true;
        }
        if (Robot.vision.isTargetVisible() && Robot.vision.getAngle()> -17) {
        	return true;
        }
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
