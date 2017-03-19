package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IsSonicOK extends Command {

    public IsSonicOK() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;

    	if(Robot.gearChute.getRangeFinderDistance() > 28.0 && Robot.gearChute.getRangeFinderDistance() < 28.7 ){
    		lcd.clear();
    		lcd.home();
    		lcd.print("UltraSonic!");
    		return false;
    	}
    	if(Robot.gearChute.getRangeFinderDistance() < 12){
    		lcd.clear();
    		lcd.home();
    		lcd.print("UltraSonic!");
    		return false;
    	}else {
    		return true;
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
