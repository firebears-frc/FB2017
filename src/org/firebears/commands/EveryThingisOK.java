package org.firebears.commands;

import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EveryThingisOK extends Command {
	long timeout;


    public EveryThingisOK() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();
    	timeout = System.currentTimeMillis() + 1000 * 10;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(System.currentTimeMillis() >= timeout){
    		return true;
    	}else{
    		LiquidCrystal lcd = RobotMap.lcd;
    		lcd.home();
    		lcd.print("Test Good");
    		lcd.setCursor(0, 1);
    		lcd.print("Remove Ribbon");

            return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
