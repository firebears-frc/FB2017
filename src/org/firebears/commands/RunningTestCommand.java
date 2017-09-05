package org.firebears.commands;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RunningTestCommand extends CommandGroup {

    public RunningTestCommand() {
    	addSequential(new IsVoltageOK(),5);
    	addSequential(new EncoderTestCommand(2));
    	addSequential(new EncoderTestCommand(3));
    	addSequential(new EncoderTestCommand(4));
    	addSequential(new EncoderTestCommand(5));
    	addSequential(new EncoderTestCommand(11));
    	addSequential(new EncoderTestCommand(12));
    	addSequential(new EncoderTestCommand(13));
    	addSequential(new IsNavxOK());
    	addSequential(new IsGearSenorOK());
    	addSequential(new IsSonicOK());
//    	addSequential(new WaitForVisionCommand());
    	addSequential(new EveryThingisOK());

    	

    	
    	
    	
    }
}
