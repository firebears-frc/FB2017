package org.firebears.commands;

import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO: Is this needed?
 */
public class PitchWheelStop extends Command {

	double d = 70;
	
    public PitchWheelStop() {
//    	requires(Robot.climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double P = RobotMap.navXBoard.getPitch();
    	double R = RobotMap.navXBoard.getRoll();
    	double PR = Math.abs(P + R);
//(RobotMap.navXBoard.getPitch() > d || RobotMap.navXBoard.getPitch() < -d) || (RobotMap.navXBoard.getRoll() > d || RobotMap.navXBoard.getRoll() < -d)
    	if( PR > d ){
			RobotMap.chassisfrontLeft.disable();
			RobotMap.chassisfrontRight.disable();
			RobotMap.chassisrearLeft.disable();
			RobotMap.chassisrearRight.disable();
//			SmartDashboard.putString("?", "Disabled");
		}else {
			RobotMap.chassisfrontLeft.enable();
			RobotMap.chassisfrontRight.enable();
			RobotMap.chassisrearLeft.enable();
			RobotMap.chassisrearRight.enable();
//			SmartDashboard.putString("?", "Enabled");

		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
