package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Automatically drives up and puts the gear on the left spike and drives away for autonomous.
 */
public class AutoGearLeftCommand extends CommandGroup {

    private static final String RECORDING_2 = "recording/auto_gear_right_2.csv";
	private static final String RECORDING_1 = "recording/auto_gear_right_1.csv";

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
		//addSequential(new MoveForwardInches(2*12));
    	addSequential(new VisionLightRingCommand(true));
    	addSequential(new MirrorPlay(RECORDING_1));
    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate());
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new WaitForGearCommand());
    	addSequential(new WaitCommand(2.0));
    	addSequential(new MirrorPlay(RECORDING_2));
    	addSequential(new VisionLightRingCommand(false));
   
    }
}
