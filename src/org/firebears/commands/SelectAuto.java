
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SelectAuto extends Command {
	

	int x = 0;
	// double rf = Robot.shooter.getRangeFinderDistance();

	public SelectAuto() {
				requires(Robot.chassis);

	}

	public void initialize() {

	}

	public void execute() {
		
// 		lcd commands
//		clear();					Clears the display at the curser
//		home();						places the curser at home(0,0)
//		setCursor(int col, int row);Yeah
//		write(int character); 		Litary useless
//		print(String message);		prints stuff
		
		
		LiquidCrystal lcd = RobotMap.lcd;
		x++;
		
		
		// Note: if the lcd goes out of bounds of the lcd it will send an error and disable the INTER ROBOT
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		System.out.println("hey");
	}

	protected void interrupted() {
	}
}