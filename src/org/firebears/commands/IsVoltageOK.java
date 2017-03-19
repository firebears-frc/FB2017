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
    	timeout = System.currentTimeMillis();
    	driverStation = DriverStation.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;

    	if(driverStation.getBatteryVoltage() < 13 ){
    		lcd.clear();
    		lcd.home();
    		lcd.print("BAT<13!");
    		return false;
    	}else{
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
