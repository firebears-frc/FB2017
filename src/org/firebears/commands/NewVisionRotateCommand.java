package org.firebears.commands;

import org.firebears.Robot;
import static org.firebears.RobotMap.boundAngle;
import static org.firebears.RobotMap.getNavXAngle;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NewVisionRotateCommand extends PIDCommand {

	long timeout;
	double turnValue;
	double targetAngle;
	double FORWARD_SPEED = .15;
	private static final double SPEED = 0.15;
	private static final double TOLERANCE = 1.0;
	
	//Amount the command overshoots from each given angle
	public final double offsetFrom20 = /*10.5;Practice Value:*/ 5.2;		//Previous Values: //10;//4.2;
	public final double offsetFrom10 = /*5;Practice Value:*/ 2.5;		//Previous Values: //6;//2.6;
	public final double offsetFrom0 = /*0.1;Practice Value:*/ .2;		//Previous Values: //2;//.1;
	
    public NewVisionRotateCommand() {
        super(0.5, 0, 0);
        
    	requires(Robot.chassis);
    	
    	getPIDController().setInputRange(-180, 180);
    	getPIDController().setContinuous();
    	getPIDController().setAbsoluteTolerance(TOLERANCE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 1000 * 5;
//    	turnValue = SmartDashboard.getNumber("New Vision Test Angle", 0);
    	turnValue = Robot.testvision.getAngleX();
    	targetAngle = boundAngle(getNavXAngle() + turnValue - getOffset(turnValue));
    	getPIDController().setSetpoint(0);
    }
    
    //To compensate for camera angle error and PID over/under error (Redneck I in PID)
    private double getOffset(double startAngle) {
    	double offsetAnswer;
    	offsetAnswer = startAngle * ((offsetFrom20 - offsetFrom10)/10) + offsetFrom0;//y = m*x + b
    	return offsetAnswer;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Hi");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double difference = getAngleDifference(); 
    	if (System.currentTimeMillis() >= timeout || Math.abs(difference) < TOLERANCE) {
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
    
    private double getAngleDifference() {
		return boundAngle(getNavXAngle() - targetAngle);//This function in robot map!
	}

	@Override
	protected double returnPIDInput() {
		return getAngleDifference();
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(FORWARD_SPEED, 0.0, output);
	}
}
