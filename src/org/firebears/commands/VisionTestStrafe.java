package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class VisionTestStrafe extends PIDCommand {

	long timeout;
	double startAngle;
	double currentAngle;
	double tolerance = 2.5;
	
    public VisionTestStrafe() {
    	super(0.025, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setInputRange(-180.0, 180.0);
    	getPIDController().setAbsoluteTolerance(tolerance);
    	getPIDController().setContinuous();
    }
    
    public double getAngleDifference(){
    	return currentAngle - startAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 5;
    	startAngle = RobotMap.navXBoard.getAngle() + Robot.vision.getAngle()/* + ( Robot.vision.getTilt() * 4 )*/;
    	getPIDController().setSetpoint(0.0);
    	
    	System.out.println("Vision Strafe Start At " + (RobotMap.navXBoard.getAngle() - startAngle) + " degrees off");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = RobotMap.navXBoard.getAngle();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	
    	if (Robot.gearChute.getRangeFinderDistance() < 15.0){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
    	System.out.println("Vision Strafe End At " + (RobotMap.navXBoard.getAngle() - startAngle) + " degrees off");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getAngleDifference();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.chassis.drive(0.5, 0.0, output);
	}
}
