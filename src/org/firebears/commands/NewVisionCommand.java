package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewVisionCommand extends CommandGroup {

    public NewVisionCommand() {
        addSequential(new NewVisionRotateCommand());
//        addSequential(new NewVisionDriveCommand());
        addSequential(new VisionForwardIntoTarget());
    }
}
