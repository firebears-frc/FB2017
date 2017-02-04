



package org.firebears.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;

/**
 *
 */
public class StopMotionRecordCommand extends Command {

    
    public StopMotionRecordCommand() {

    
    }

   
    protected void initialize() {
    	StartMotionRecordCommand.recording = false;
    }

   
    protected void execute() {
    }

   
    protected boolean isFinished() {
        return true;
    }

  
    protected void end() {
    }

    
    
    protected void interrupted() {
    }
}
