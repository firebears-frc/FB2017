package org.firebears.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.firebears.Robot;
import org.firebears.RobotMap;

/**
 * This command drives the robot forward or backward so that the vision camera faces the target.
 */
public class VisionAngleAdjustCommand extends MoveForwardInches {

	public VisionAngleAdjustCommand() {
		super(0.0);
	}

	@Override
    protected void initialize() {
    	Robot.vision.setLightRingOn();
		moveDistance = -1.0 * (Robot.vision.getDistance() * Math.sin(Robot.vision.getAngle() * Math.PI / 180));
		super.initialize();
		System.out.printf("::: %b distance=%4.2f  angle=%3.1f  moveDistance=%4.2f %n", 
				Robot.vision.isTargetVisible(), Robot.vision.getDistance(), Robot.vision.getAngle(), moveDistance);
	}
	
	protected void execute() {
		if (Robot.vision.isTargetVisible() == false){
			end();
		}
	}
	
    protected void end() {
    	super.end();
    	Robot.vision.setLightRingOff();
    }

}

/*public class VisionAngleAdjustCommand extends PIDCommand {

	float turnValue;
	float driveValue;
	long timeout;
	float targetAngle;
	protected final double SPEED = 0.25;
	protected double angleTolerance = 10.0;
	
    public VisionAngleAdjustCommand() {
    	//PID Values not correct
    	super(0.1, 0.0, 0.0);
        requires(Robot.chassis);
//        driveValue = (float)distance;
        
		getPIDController().setAbsoluteTolerance(angleTolerance);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
//    	RobotMap.gearLightRing.set(Relay.Value.kForward);
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	turnValue = Robot.vision.getAngle();
    	targetAngle = 0;
    	getPIDController().setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	double difference = getAngleDifference();
//    	SmartDashboard.putString("End:", "" + difference);
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
    	
    	if (Robot.vision.getAngle() < angleTolerance){
//    		RobotMap.gearLightRing.set(Relay.Value.kOff);
    		return true;
    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDriving();
//    	RobotMap.gearLightRing.set(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return Robot.vision.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(0.0, -output, 0.0);
	}
	
	@Override
    public String toString() {
    	return "VisionAngleCommand(" + targetAngle + ")";
    }
}*/