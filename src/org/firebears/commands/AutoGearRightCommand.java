package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically drives up and puts the gear on the right spike and drives away for autonomous.
 */
public class AutoGearRightCommand extends CommandGroup {

    public AutoGearRightCommand() {
    	
    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_1.csv"));
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate(false));
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new GearWaitCommand());
    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_2.csv"));
    	addSequential(new VisionLightRingCommand(false));
    }
}
