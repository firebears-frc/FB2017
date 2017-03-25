package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Automatically drives up and puts the gear on the middle spike and drives away for autonomous.
 */
public class AutoGearMiddleCommand extends CommandGroup {

    public AutoGearMiddleCommand() {
    	//addSequential(new MoveForwardInches(3*12));
    	addSequential(new VisionLightRingCommand(true));
//    	addSequential(new MoveForwardInches(3.0));
//    	addSequential(new WaitCommand(0.5));
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate());
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new WaitForGearCommand());
    	addSequential(new WaitCommand(2.0));
//    	addSequential(new PlayRecordingCommand("recording/auto_gear_middle_1.csv"));
    	addSequential(new VisionLightRingCommand(false));
    }
}
