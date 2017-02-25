package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically drives up and puts the gear on the left spike and drives away for autonomous.
 */
public class AutoGearLeftCommand extends CommandGroup {

    public AutoGearLeftCommand() {
// drive forward
// turn 30 degrees right
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate(false));
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new GearWaitCommand());
// add the command below for the left recording after delivering the gear.
//  	addSequential(new PlayRecordingCommand("recording/auto_gear_middle_1.csv"));
   
    }
}
