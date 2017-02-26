package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StrafeStraightCommand extends PIDCommand {

	protected final double SPEED = 0.3;
	

	protected final double DRIFTCOMPENSATION = 0.0;//Hack for Competition RObot			
	
	long timeout;
	double startAngle;//Navex starting angle
	double currentAngle;//Navex angle as robot is driving
	double tolerance = 2.5;
	
    public StrafeStraightCommand() {
    	super(0.025, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setInputRange(-180.0, 180.0);
    	getPIDController().setAbsoluteTolerance(tolerance);
    }
    
    public double getAngleDifference(){
    	return currentAngle - startAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setLightRingOn();
    	RobotMap.navXBoard.reset();
    	timeout = System.currentTimeMillis() + 1000 * 5;
//    	startAngle = RobotMap.navXBoard.getAngle();
    	getPIDController().setSetpoint(0.0);
//    	SmartDashboard.putNumber("Strafe Start Angle:", startAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	currentAngle = RobotMap.navXBoard.getAngle();
//    	SmartDashboard.putNumber("Strafe Current Angle:", currentAngle);
//    	SmartDashboard.putNumber("Strafe Angle Difference:", getAngleDifference());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return RobotMap.getNavXAngle() /*+ Robot.vision.getAngle()*/;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.chassis.drive(-0.3, DRIFTCOMPENSATION, output);
	}
}
