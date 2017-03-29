package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IsVoltageOK extends Command {
	DriverStation driverStation;
	
	
	long timeout;


    public IsVoltageOK() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();
    	timeout = System.currentTimeMillis();
    	driverStation = DriverStation.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.setCursor(0, 1);
		lcd.print(String.format("%6.2f",driverStation.getBatteryVoltage()));

		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;

    	if(driverStation.getBatteryVoltage() < 13 ){
    		lcd.home();
    		lcd.print("BAT<13!");
    		return false;
    	}else{
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
