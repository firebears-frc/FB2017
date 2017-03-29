package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IsGearSenorOK extends Command {
	
	boolean currentPos;
	
	
    public IsGearSenorOK() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();
    	
    	currentPos = Robot.gearChute.isGearInChute();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.setCursor(0, 1);
		lcd.print("" + Robot.gearChute.isGearInChute());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;
		
    	if(currentPos == false && Robot.gearChute.isGearInChute() == false){
    		lcd.home();
    		lcd.print("GearChuteSensor!");
    		return false;
    	}if(currentPos == true && Robot.gearChute.isGearInChute() == true){
    		lcd.home();
    		lcd.print("GearChuteSensor!");
    		return false;
    	}else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
