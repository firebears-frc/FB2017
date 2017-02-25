package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically drives up and puts the gear on the left spike and drives away for autonomous.
 */
public class AutoGearLeftCommand extends CommandGroup {

    public AutoGearLeftCommand() {
// drive forward
// turn 30 degrees right
    	addSequential(new PlayRecordingCommand("recording/auto_gear_left_1.csv"));
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate(false));
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new WaitForGearCommand());
// add the command below for the left recording after delivering the gear.
    	addSequential(new PlayRecordingCommand("recording/auto_gear_left_2.csv"));
    	addSequential(new VisionLightRingCommand(false));
   
    }
}
