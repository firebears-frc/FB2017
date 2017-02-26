package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Wait x seconds for Vision. If vision is connected or time expires the command
 * ends.
 */
public class WaitForGearCommand extends Command {

	long timeout;

	public WaitForGearCommand() {
		requires(Robot.chassis);
	}

	protected void initialize() {
		timeout = System.currentTimeMillis() + 1000 * 5;
	}

	protected void execute() {
	}

	/**
	 * Finishes when gear is no longer in chute, or after timeout.
	 */
	protected boolean isFinished() {
		if (System.currentTimeMillis() >= timeout) {
			return true;
		}
		return !Robot.gearChute.isGearInChute();
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
