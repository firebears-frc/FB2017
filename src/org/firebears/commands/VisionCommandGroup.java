package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * The complete vision process.
 */
public class VisionCommandGroup extends CommandGroup {

    public VisionCommandGroup() {
    	// Drive the robot backwards or forwards so that the vision camera faces the target
    	addSequential(new VisionAngleAdjustCommand());
    	addSequential(new WaitCommand(1.5));
    	
    	// Rotate the robot based on difference between sizes of targets so that vision camera is straight on the target
    	addSequential(new VisionRotate());
    	addSequential(new WaitCommand(1.5));
    	
    	// Drive straight until the robot hits the target and gets the gear on it.
    	addSequential(new VisionTestStrafe());
//    	addSequential(new VisionDrive());
    }
}
