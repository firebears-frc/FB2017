package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * The complete vision process.
 */
public class VisionCommandGroup extends CommandGroup {

    public VisionCommandGroup() {
    	// Turn the light ring on so vision will see it properly
    	addSequential(new VisionLightRingCommand(true));
    	addSequential(new WaitForVisionCommand());
    	
    	// Rotate the robot based on difference between sizes of targets so that vision camera is straight on the target
    	addSequential(new VisionRotate());
    	addSequential(new WaitCommand(1.0));
    	
    	// Drive the robot backwards or forwards so that the vision camera faces the target
//    	addSequential(new VisionAngleAdjustCommand());
//    	addSequential(new WaitCommand(1.0));
    	
    	// Drive straight until the robot hits the target and gets the gear on it.
//    	addSequential(new StrafeStraightCommand());
    	addSequential(new VisionForwardIntoTarget());
    	addSequential(new VisionLightRingCommand(false));
//    	addSequential(new VisionDrive());
    }
}
