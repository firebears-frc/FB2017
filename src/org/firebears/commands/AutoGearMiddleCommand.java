package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically drives up and puts the gear on the middle spike and drives away for autonomous.
 */
public class AutoGearMiddleCommand extends CommandGroup {

    public AutoGearMiddleCommand() {
    	addSequential(new MoveForwardInches(-3*12));
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate(false));
    	addSequential(new VisionBackIntoTarget());
    	// TODO:   wait for gear
    	addSequential(new PlayRecordingCommand("recording/auto_gear_middle_1.csv"));
    }
}
