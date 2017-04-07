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
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();

		RobotMap.chassisfrontLeft.set(0);
		RobotMap.chassisfrontRight.set(0);
		RobotMap.chassisrearRight.set(0);
		RobotMap.climberclimbMotor.set(0);
		RobotMap.floorfloorLift.set(0);
		RobotMap.acquisitionacquisitionMotor.set(0);

		
		}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		Robot.chassis.drive(0, .5, 0);
//		RobotMap.chassisfrontLeft.set(50);
		LiquidCrystal lcd = RobotMap.lcd;

		
		

		
		if(talonNum == 2){
			RobotMap.chassisfrontLeft.set(150);
			lcd.setCursor(0, 1);
			lcd.print("" + RobotMap.chassisfrontLeft.getEncVelocity());
			lcd.setCursor(0, 2);
			lcd.print("" + RobotMap.chassisfrontLeft.getEncPosition());
			
			

		}else if(talonNum == 3){
			RobotMap.chassisfrontRight.set(150);
			lcd.setCursor(0, 1);
			lcd.print("" + RobotMap.chassisfrontRight.getEncVelocity());
			lcd.setCursor(0, 2);
			lcd.print("" + RobotMap.chassisfrontRight.getEncPosition());
			

		}else if(talonNum == 4){
			RobotMap.chassisrearLeft.set(150);	
			lcd.setCursor(0, 1);
			lcd.print("" + RobotMap.chassisrearLeft.getEncVelocity());
			lcd.setCursor(0, 2);
			lcd.print("" + RobotMap.chassisrearLeft.getEncPosition());

		}else if(talonNum == 5){
			RobotMap.chassisrearRight.set(150);	
			lcd.setCursor(0, 1);
			lcd.print("" + RobotMap.chassisrearRight.getEncVelocity());
			lcd.setCursor(0, 2);
			lcd.print("" + RobotMap.chassisrearRight.getEncPosition());

		}else if(talonNum == 11){
			RobotMap.climberclimbMotor.set(.5);	

		}else if(talonNum == 12){
			RobotMap.floorfloorLift.set(.75);	

		}else if(talonNum == 13){
			RobotMap.acquisitionacquisitionMotor.set(.25);	

		}
		
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		LiquidCrystal lcd = RobotMap.lcd;

		if(talonNum == 2 && Math.abs(RobotMap.chassisfrontLeft.getEncVelocity()) < 30){
    		lcd.home();
    		lcd.print("Talon2FL/SIM");
    		RobotMap.chassisfrontLeft.set(0);
    		
			
			return false;
		}else if(talonNum == 3 && Math.abs(RobotMap.chassisfrontRight.getEncVelocity()) < 30){
			lcd.home();
    		lcd.print("Talon3FR/SIM");
    		RobotMap.chassisfrontRight.set(0);
    		
			
			return false;
		}else if(talonNum == 4 && Math.abs(RobotMap.chassisrearLeft.getEncVelocity()) < 30){
    		lcd.home();
    		lcd.print("Talon4RL/SIM");
    		RobotMap.chassisrearLeft.set(0);
    		
			
			return false;
		}else if(talonNum == 5 && Math.abs(RobotMap.chassisrearRight.getEncVelocity()) < 30){
    		lcd.home();
    		lcd.print("Talon5RR/SIM");
    		RobotMap.chassisrearRight.set(0);
			
			return false;
		}else if(talonNum == 11 && RobotMap.climberclimbMotor.getOutputCurrent() < 2){
    		lcd.home();
    		lcd.print("Talon11Climber/SIM");
    		RobotMap.climberclimbMotor.set(0);
			
			return false;
		}else if(talonNum == 12 && RobotMap.floorfloorLift.getOutputCurrent() < 2){
    		lcd.home();
    		lcd.print("Talon12Floor/SIM");
    		RobotMap.floorfloorLift.set(0);
			
			return false;
		}else if(talonNum == 13 && RobotMap.acquisitionacquisitionMotor.getOutputCurrent() < 2){
    		lcd.home();
    		lcd.print("Talon13acq/SIM");
    		RobotMap.acquisitionacquisitionMotor.set(0);
			
			return false;
		}else{
			return true;
		}
		
		
	}

	// Called once after isFinished returns true
	protected void end() {
		LiquidCrystal lcd = RobotMap.lcd;
		lcd.clear();

		RobotMap.chassisfrontLeft.set(0);
		RobotMap.chassisfrontRight.set(0);
		RobotMap.chassisrearLeft.set(0);
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
