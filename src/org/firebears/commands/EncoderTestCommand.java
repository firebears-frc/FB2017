package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

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
		
		if(talonNum == 2){
			RobotMap.chassisfrontLeft.set(.1);	

		}else if(talonNum == 3){
			RobotMap.chassisfrontRight.set(.1);	

		}else if(talonNum == 4){
			RobotMap.chassisrearLeft.set(.1);	

		}else if(talonNum == 5){
			RobotMap.chassisrearRight.set(.1);	

		}
		
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.chassisfrontLeft.set(0);
		RobotMap.chassisfrontRight.set(0);
		RobotMap.chassisrearRight.set(0);
		RobotMap.chassisrearLeft.set(0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
