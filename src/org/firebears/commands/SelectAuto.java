
package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Cycle through different autonomous's when the auto switch button is pressed, changing the LCD.
 */
public class SelectAuto extends Command {
//	DriverStation driverStation;
	int x = 0;
	
	Command[] commandlist = {
//			new StrafeInches(12, .5),
//			new MoveForwardInches(12),
			new AutoGearLeftCommand(),
			new AutoGearMiddleCommand(),
			new AutoGearRightCommand(),
	};

	// double rf = Robot.shooter.getRangeFinderDistance();

	public SelectAuto() {
		// requires(Robot.chassis);
//    	driverStation = DriverStation.getInstance();
	}
	
//	public void debug(){
//		LiquidCrystal lcd = RobotMap.lcd;
//		lcd.home();
//		lcd.print("Debugging:");
//		lcd.setCursor(11, 0);
//		lcd.print(String.format("%6.2f", driverStation.getMatchTime()));
//
//	}

	public void initialize() {
		LiquidCrystal lcd = RobotMap.lcd;

		lcd.home();
		lcd.print("Auto:");
	}

	public void execute() {
		LiquidCrystal lcd = RobotMap.lcd;


		// lcd commands
		// clear(); Clears the display at the curser
		// home(); places the curser at home(0,0)
		// setCursor(int col, int row);Yeah
		// write(int character); Litary useless
		// print(String message); prints stuff

		x++;
		
		if (x >= commandlist.length){
    		x = 0;
        	Robot.autonomousCommand = commandlist[x];

    	}
		lcd.home();
		lcd.print("Auto:");
		
		lcd.home();
		
		String name = commandlist[x].toString();
		
    	lcd.print(name);
    	
    	SmartDashboard.putString("Autonomous:", name);

		// Note: if the lcd goes out of bounds of the lcd it will send an error
		// and disable the INTER ROBOT
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
    	LiquidCrystal lcd = RobotMap.lcd;
    	lcd.clear();
	}

	protected void interrupted() {
	}
}