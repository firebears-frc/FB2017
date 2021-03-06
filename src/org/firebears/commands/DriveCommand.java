// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.firebears.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;


/**
 * Command that runs continuously, turns joystick input into robot driving.
 */
public class DriveCommand extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    
    double strafe3 = 0.0;
    double forward3 = 0.0;
    double rotation3 = 0.0;
//    double strafe4 = 0.0;
//    double forward4 = 0.0;
//    double rotation4 = 0.0;
//    double oy = 0.0;
//    double ox = 0.0;
//    double oz = 0.0;

    // TODO: Make this legible.
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	//Drive with one Joystick
    	double strafe = Robot.oi.joystick1.getRawAxis(0);
    	double forward = Robot.oi.joystick1.getRawAxis(1);
    	double rotation = Robot.oi.joystick1.getRawAxis(2);
    	
//    	double forward = Robot.oi.joystick1.getY();
//    	double strafe = Robot.oi.joystick1.getX(); 
//    	double rotation = Robot.oi.joystick1.getZ();
    	
    	//Drive with two Joysticks
//    	double forward = Robot.oi.joystick1.getY();
//    	double strafe = Robot.oi.joystick1.getX(); 
//    	double rotation = Robot.oi.joystick2.getX();
    	
    	
    	//set to minimum joystick value were the robot moves(Only do this if you do NOT have PID Driving!AKA lEAVE AT ZERO WHEN PID)
    	double robotdedy = 0.00;
    	double robotdedx = 0.00;
    	double robotdedz = 0.00;
    	
    	//Deadzone of the joysticks
    	double Joydedy = 0.11;
    	double Joydedx = 0.04;
    	double Joydedz = 0.07;
    	
    	//Add a powercurve (((senativaty in the lower and upper values)))
//    	double sensxy = 1.1;
//    	double sensez = 1.15;
    	
//    	SmartDashboard.putNumber("JoyY", forward);
//    	SmartDashboard.putNumber("JoyX", strafe);
//    	SmartDashboard.putNumber("JoyZ", rotation);

    	double y2 = forward * (1 - robotdedy)/1;
    	double x2 = strafe * (1 - robotdedx)/1;
    	double z2 = rotation * (1 - robotdedz)/1;
    		
    	
    	//Foward Deadzone
    	if (forward > Joydedy){
    		forward3 = (y2 + robotdedy - Joydedy) * ((1 + Joydedy)/1);
    	}
    	
    	else if (forward < -Joydedy){
    		forward3 = (y2 - robotdedy + Joydedy) * ((1 + Joydedy)/1);
    	}
    	else if (forward < Joydedy && forward > -Joydedy)
    		forward3 = 0.0;
    	
    	//Strafe Deadzone
    	if (strafe > Joydedy){
    		strafe3 = (x2 + robotdedx - Joydedx) * ((1 + Joydedx)/1);
    	}
    	
    	else if (strafe < -Joydedx){
    		strafe3 = (x2 - robotdedx + Joydedx) * ((1 + Joydedx)/1);
    	}
    	else if (strafe < Joydedx && strafe > -Joydedx)
    		strafe3 = 0.0;
    	//Rotation Deadzone
    	if (rotation > Joydedz){
    		rotation3 = (z2 + robotdedz - Joydedz) * ((1 + Joydedz)/1);
    	}
    	
    	else if (rotation < -Joydedz){
    		rotation3 = (z2 - robotdedz + Joydedz) * ((1 + Joydedz)/1);
    	}
    	else if (rotation < Joydedz && rotation > - Joydedz)
    		rotation3 = 0.0;
    	
    	//strafe sense
    	
//    	if (strafe < 0.0){
//    		double ox = strafe3 * -1;
//    		strafe4 = -Math.pow(ox, sensxy);
//    	}
//    	
//    	else if (strafe > 0.0){
//    		strafe4 = Math.pow(strafe3, sensxy);
//    	}
//    	
//    	//forward sense
//    	
//    	if (forward > 0.0){
//    		forward4 = Math.pow(forward3, sensxy);
//    	}
//    	else if (forward < 0.0){
//    		double oy = forward3 * -1;
//    		forward4 = -Math.pow(oy, sensxy);
//    	}
//    	
//    	//rotation sense
//    	
//    	if (rotation < 0.0){
//    		double oz = rotation3 * -1;
//    		rotation4 = -Math.pow(oz, sensez);
//    	}
//    	else if (rotation > 0.0){
//    		rotation4 = Math.pow(rotation3, sensez);
//    	}
    	
//    	SmartDashboard.putNumber("Rotation4", rotation3);
//    	SmartDashboard.putNumber("JoyZ", rotation);
//    	SmartDashboard.putNumber("Strafe4", strafe3);
//    	SmartDashboard.putNumber("JoyX", strafe);
//    	SmartDashboard.putNumber("Forward4", forward3);
//    	SmartDashboard.putNumber("JoyY", forward);

    	
    	Robot.chassis.drive(strafe3, forward3, rotation3 * 0.5);
    	
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
