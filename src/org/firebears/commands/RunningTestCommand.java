package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RunningTestCommand extends CommandGroup {

    public RunningTestCommand() {
//      addSequential(new IsGearSenorOK());
    	addSequential(new IsSonicOK());
    	addSequential(new IsVoltageOK());
//    	addSequential(new EncoderTestCommand(2));
//    	addSequential(new EncoderTestCommand(3));
//    	addSequential(new EncoderTestCommand(4));
//    	addSequential(new EncoderTestCommand(5));
//    	addSequential(new EncoderTestCommand(11));
//    	addSequential(new EncoderTestCommand(12));
//    	addSequential(new EncoderTestCommand(13));

    	

    	
    	
    	
    }
}
