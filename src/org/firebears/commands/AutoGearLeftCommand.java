package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically drives up and puts the gear on the left spike and drives away for autonomous.
 */
public class AutoGearLeftCommand extends CommandGroup {

    public AutoGearLeftCommand() {
    	
//    	addSequential(new MoveForwardInches(36, .66));
//    	addSequential(new WaitCommand(.2));
//    	addSequential(new AutoRotate(30),2.0);
//    	addSequential(new VisionLightRingCommand(true));
//    	addSequential(new WaitCommand(1.0));
//    	addSequential(new VisionRotate(false));
//    	addSequential(new VisionForwardIntoTarget());
//    	addSequential(new WaitForGearCommand());
//    	addSequential(new WaitCommand(1.75));
//    	if (Robot.gearChute.isGearInChute()){ //TODO: This is BAD no ifs in constructors!
//    		addSequential(new MoveForwardInches(-24, .5));
//        	addSequential(new WaitCommand(.2));
//        	addSequential(new AutoRotate(-30),2.0);
//        	addSequential(new WaitCommand(.2));
//        	addSequential(new MoveForwardInches(60, .66));
//        	addSequential(new VisionLightRingCommand(false));
//    	}
    	
    	
    	// TODO: Should add this line.
    	// addSequential(new VisionLightRingCommand(true));
// drive forward and turn 30 degrees right
    	addSequential(new PlayRecordingCommand("recording/auto_gear_left_1.csv"));
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate());
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new WaitForGearCommand());
//// add the command below for the left recording after delivering the gear.
    	addSequential(new PlayRecordingCommand("recording/auto_gear_left_2.csv"));
    	addSequential(new VisionLightRingCommand(false));
   
    }
}
