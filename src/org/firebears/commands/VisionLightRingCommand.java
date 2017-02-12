package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Turn the light ring on or off.
 */
public class VisionLightRingCommand extends InstantCommand {

	final boolean turnOn;

	public VisionLightRingCommand(boolean turnOn) {
		this.turnOn = turnOn;
	}

	protected void initialize() {
		if (turnOn) {
			Robot.vision.setLightRingOn();
		} else {
			Robot.vision.setLightRingOff();
		}
	}

	protected void execute() {
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}
