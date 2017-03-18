package org.firebears.commands;

import org.firebears.*;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.firebears.RobotMap.boundAngle;
import static org.firebears.RobotMap.getNavXAngle;

/**
 *
 */
public class VisionRotate extends PIDCommand {

	float turnValue;
	float driveValue;
	long timeout;
	double targetAngle;
	private static final double SPEED = 0.15;
	private static final double ANGLE_TOLERANCE = 1.0;
	
	//Amount the command overshoots from each given angle
	public final double offsetFrom20 = /*10.5;Practice Value:*/ 12;		//Previous Values: //10;//4.2;
	public final double offsetFrom10 = /*5;Practice Value:*/ 6.7;		//Previous Values: //6;//2.6;
	public final double offsetFrom0 = /*0.1;Practice Value:*/ .4;		//Previous Values: //2;//.1;
	
    public VisionRotate() {
    	//PID Values not correct
    	super(0.5, 0.0, 0.0);
        requires(Robot.chassis);
//        driveValue = (float)distance;
        
        getPIDController().setContinuous(true);
		getPIDController().setInputRange(-180.0, 180.0);
		getPIDController().setAbsoluteTolerance(ANGLE_TOLERANCE);		
    }
    
    private double getOffset(double startAngle) {
    	double offsetAnswer;
    	offsetAnswer = startAngle * ((offsetFrom20 - offsetFrom10)/10) + offsetFrom0;
    	return offsetAnswer;
    }
    
    private double getAngleDifference() {
		return boundAngle(getNavXAngle() - targetAngle);
	}
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setLightRingOn();
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	turnValue = Robot.vision.getAngle();
    	targetAngle = boundAngle(getNavXAngle() + turnValue/* - getOffset(turnValue)*/);
    	getPIDController().setSetpoint(0);
    	SmartDashboard.putNumber("VisionTarget:", targetAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double difference = getAngleDifference();
    	SmartDashboard.putNumber("Angle Difference:", difference);
    	if (System.currentTimeMillis() >= timeout || Math.abs(difference) < ANGLE_TOLERANCE) {
    		return true;
    	}    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
//    	Robot.vision.setLightRingOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return getAngleDifference();
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(0.0, 0.0, output);
	}
	
	@Override
    public String toString() {
    	return "RotationCommand(" + targetAngle + ")";
    }
}
