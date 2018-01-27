package org.firebears.commands;

import org.firebears.*;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.firebears.RobotMap.boundAngle;
import static org.firebears.RobotMap.getNavXAngle;

/**
 * Vision rotation used in autonomous.
 */
public class VisionRotate extends PIDCommand {

	float turnValue;
	float driveValue;
	long timeout;
	double targetAngle;
	private static final double SPEED = 0.15;
	private static final double ANGLE_TOLERANCE = 1.0;
	
	//Amount the command overshoots from each given angle
	public final double offsetFrom20 = /*10.5;Practice Value:*/ 5.2;		//Previous Values: //10;//4.2;
	public final double offsetFrom10 = /*5;Practice Value:*/ 2.5;		//Previous Values: //6;//2.6;
	public final double offsetFrom0 = /*0.1;Practice Value:*/ .2;		//Previous Values: //2;//.1;
	
    public VisionRotate() {
    	//PID Values not correct
    	super(0.5, 0.0, 0.0);
        requires(Robot.chassis);
//        driveValue = (float)distance;
        
		getPIDController().setInputRange(-180.0, 180.0);
        getPIDController().setContinuous(true);
		getPIDController().setAbsoluteTolerance(ANGLE_TOLERANCE);		
    }
    
    //TODO  a)Futz with PID and speed without offset to get as close as possible to correct turn angle. 
    //    	If initial angle beyond 20 degrees we may be getting a false "valid" reading from vision, check this out.
    //		If we skip over the value less than 1, (in isFinished()) this will continue to timeout!
    //TODO  b)Swing robot from 20degrees off and write down error from smart dashboard, average 3 times, do the same for 10 degrees
    //TODO  c)Align robot with target so robot exactly perpendicular with target and camera aligned with target, get zero offset. 
    //TODO  d)Then uncomment getOffset() in initialize and verify within 1 degree in  turn.
    
   
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.setLightRingOn();
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	turnValue = Robot.vision.getAngle();
    	targetAngle = boundAngle(getNavXAngle() + turnValue - getOffset(turnValue));
    	getPIDController().setSetpoint(0);
    	SmartDashboard.putNumber("Vision Angle:", targetAngle);
    }
    
    //To compensate for camera angle error and PID over/under error (Redneck I in PID)
    private double getOffset(double startAngle) {
    	double offsetAnswer;
    	offsetAnswer = startAngle * ((offsetFrom20 - offsetFrom10)/10) + offsetFrom0;//y = m*x + b
    	return offsetAnswer;
    }
    
 
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    //If we skip over the value less than 1, this will continue to timeout!
    protected boolean isFinished() {
    	double difference = getAngleDifference();
    	SmartDashboard.putNumber("Angle Difference:", difference);  
    	if (System.currentTimeMillis() >= timeout || Math.abs(difference) < ANGLE_TOLERANCE) {
    		return true;
    	}    	
    	return false;
    }
    
    
    private double getAngleDifference() {
		return boundAngle(getNavXAngle() - targetAngle);//This function in robot map!
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
