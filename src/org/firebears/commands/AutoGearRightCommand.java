package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Automatically drives up and puts the gear on the right spike and drives away for autonomous.
 */
public class AutoGearRightCommand extends CommandGroup {

    public AutoGearRightCommand() {
    	
////    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_1.csv"));
//    	addSequential(new MoveForwardInches(100, .66));
//    	addSequential(new WaitCommand(.2));
//    	addSequential(new AutoRotate(-30),2.0);
//    	addSequential(new VisionLightRingCommand(true));
//    	addSequential(new WaitCommand(1.0));
//    	addSequential(new VisionRotate(false));
//    	addSequential(new VisionForwardIntoTarget());
//    	addSequential(new WaitForGearCommand());
//    	addSequential(new WaitCommand(1.75));
////    	if (Robot.gearChute.isGearInChute()){ //TODO: This is BAD no ifs in constructors!
//    		addSequential(new MoveForwardInches(-50, .5));
//    		addSequential(new WaitCommand(.2));
//    		addSequential(new AutoRotate(30),2.0);
//    		addSequential(new WaitCommand(.2));
//    		addSequential(new MoveForwardInches(50, .66));
//    		addSequential(new VisionLightRingCommand(false));
////    	}
    	
    	// TODO: Should add this line.
    	// addSequential(new VisionLightRingCommand(true));
    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_1.csv"));
//    	addSequential(new WaitForVisionCommand());
    	addSequential(new VisionRotate());
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new WaitForGearCommand());
    	addSequential(new WaitCommand(2.0));
    	// TODO: Should match with AutoGearLeftCommand
    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_2.csv"));
    	addSequential(new VisionLightRingCommand(false));
    	
//    	addSequential(new WaitForVisionCommand());
//    	addSequential(new VisionRotate(false));
//    	addSequential(new VisionForwardIntoTarget());
//    	addSequential(new WaitForGearCommand());
////    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_2.csv"));
//    	addSequential(new VisionLightRingCommand(false));
    }
}
