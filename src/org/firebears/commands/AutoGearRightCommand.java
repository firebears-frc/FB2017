package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Automatically drives up and puts the gear on the right spike and drives away for autonomous.
 */
public class AutoGearRightCommand extends CommandGroup {

    public AutoGearRightCommand() {
    	
//    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_1.csv"));
    	addSequential(new MoveForwardInches(36, .66));
    	addSequential(new WaitCommand(.2));
    	addSequential(new AutoRotate(-30),2.0);
    	addSequential(new VisionLightRingCommand(true));
    	addSequential(new WaitCommand(1.0));
    	addSequential(new VisionRotate(false));
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new WaitForGearCommand());
    	addSequential(new WaitCommand(1.75));
    	if (Robot.gearChute.isGearInChute()){
    		addSequential(new MoveForwardInches(-24, .5));
    		addSequential(new WaitCommand(.2));
    		addSequential(new AutoRotate(30),2.0);
    		addSequential(new WaitCommand(.2));
    		addSequential(new MoveForwardInches(60, .66));
    		addSequential(new VisionLightRingCommand(false));
    	}
    	







    	
    	
//    	addSequential(new WaitForVisionCommand());
//    	addSequential(new VisionRotate(false));
//    	addSequential(new VisionForwardIntoTarget());
//    	addSequential(new WaitForGearCommand());
////    	addSequential(new PlayRecordingCommand("recording/auto_gear_right_2.csv"));
//    	addSequential(new VisionLightRingCommand(false));
    }
}
