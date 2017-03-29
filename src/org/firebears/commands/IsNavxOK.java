package org.firebears.commands;

import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IsNavxOK extends Command {
	double value;

    public IsNavxOK() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();
    	value = RobotMap.navXBoard.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;
    	if(RobotMap.navXBoard.getYaw() != value){
    		return true;
    	}else {
    		lcd.home();
    		lcd.print("NAVX!");
    		lcd.setCursor(0, 1);
    		lcd.print("" + RobotMap.navXBoard.getYaw());//String.format("%6.2f", RobotMap.navXBoard.getYaw()));
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
