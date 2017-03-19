package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.util.LiquidCrystal;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderTestCommand extends Command {
	int talonNum = 0;

	public EncoderTestCommand(int talon) {
		
		talonNum = talon;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		
		}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		Robot.chassis.drive(0, .5, 0);
//		RobotMap.chassisfrontLeft.set(50);

		
		if(talonNum == 2){
			RobotMap.chassisfrontLeft.set(50);

		}else if(talonNum == 3){
			RobotMap.chassisfrontRight.set(50);	

		}else if(talonNum == 4){
			RobotMap.chassisrearLeft.set(50);	

		}else if(talonNum == 5){
			RobotMap.chassisrearRight.set(50);	

		}else if(talonNum == 11){
			RobotMap.climberclimbMotor.set(1);	

		}else if(talonNum == 12){
			RobotMap.floorfloorLift.set(1);	

		}else if(talonNum == 13){
			RobotMap.acquisitionacquisitionMotor.set(50);	

		}
		
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;

		if(talonNum == 2 && RobotMap.chassisfrontLeft.getEncVelocity() < 30){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon2FL/SIM");
			
			return false;
		}else if(talonNum == 3 && RobotMap.chassisfrontRight.getEncVelocity() < 30){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon3FR/SIM");
			
			return false;
		}else if(talonNum == 4 && RobotMap.chassisrearLeft.getEncVelocity() < 30){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon4RL/SIM");
			
			return false;
		}else if(talonNum == 5 && RobotMap.chassisrearRight.getEncVelocity() < 30){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon5RR/SIM");
			
			return false;
		}else if(talonNum == 11 && RobotMap.climberclimbMotor.getOutputCurrent() > 5){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon11Climber/SIM");
			
			return false;
		}else if(talonNum == 12 && RobotMap.floorfloorLift.getOutputCurrent() < 5){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon12Floor/SIM");
			
			return false;
		}else if(talonNum == 13 && RobotMap.acquisitionacquisitionMotor.getOutputCurrent() < 5){
			lcd.clear();
    		lcd.home();
    		lcd.print("Talon13acq/SIM");
			
			return false;
		}else{
			return true;
		}
		
		
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.chassisfrontLeft.set(0);
		RobotMap.chassisfrontRight.set(0);
		RobotMap.chassisrearRight.set(0);
		RobotMap.climberclimbMotor.set(0);
		RobotMap.floorfloorLift.set(0);
		RobotMap.acquisitionacquisitionMotor.set(0);


	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
