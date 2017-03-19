
package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
//import org.firebears.commands.defenses.ChevalDeFriseCommand;
//import org.firebears.commands.defenses.FlatCommand;
//import org.firebears.commands.defenses.FlatfiveCommand;
//import org.firebears.commands.defenses.LowBarCommand;
//import org.firebears.commands.defenses.MoatCommand;
//import org.firebears.commands.defenses.PortcullisCommand;
//import org.firebears.commands.defenses.RampartsCommand;
//import org.firebears.commands.defenses.RockWallCommand;
//import org.firebears.commands.defenses.RoughTerrainCommand;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.command.Command;

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
    	


		

		// Note: if the lcd goes out of bounds of the lcd it will send an error
		// and disable the INTER ROBOT
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}