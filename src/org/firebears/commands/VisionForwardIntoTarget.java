package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import static org.firebears.RobotMap.boundAngle;
import static org.firebears.RobotMap.getNavXAngle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class VisionForwardIntoTarget extends PIDCommand {

	long timeout;
	double startAngle;
	double currentAngle;
	double tolerance = 2.5;
	
    public VisionForwardIntoTarget() {
    	super(0.025, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setInputRange(-180.0, 180.0);
    	getPIDController().setAbsoluteTolerance(tolerance);
    	getPIDController().setContinuous();
    }
    
    public double getAngleDifference(){
		return boundAngle(getNavXAngle() - startAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 2;
    	startAngle = getNavXAngle() /* + ( Robot.vision.getTilt() * 4 )*/;
    	getPIDController().setSetpoint(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = getNavXAngle();
    	if (Robot.vision.isTargetVisible() == false){
			end();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	
    	if (Robot.gearChute.getRangeFinderDistance() < 6.9){
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
		return getAngleDifference();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.chassis.drive(0.0, -0.3, output);
	}
}
