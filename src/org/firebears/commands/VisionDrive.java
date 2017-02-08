package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class VisionDrive extends PIDCommand {

	float targetDistance;
	float currentDistance;
	
	protected final double SPEED = 0.5;
	
	long timeout;
	
	double tolerance = 1.0;
	double angleTolerance = 2.5;
	
	PIDController driveStraight;
	
	private PIDSource source;
	
	public double startAngle;
	public double currentAngle;
	
	double adjust;
	
    public VisionDrive() {
    	super(1.0, 0.0, 0.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	getPIDController().setAbsoluteTolerance(tolerance);
    	
    	source = new PIDSource() {
    		
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				setInputRange(-180.0, 180.0);
	//	    	setAbsoluteTolerance(tolerance);
			}
			
			@Override
			public double pidGet() {
				return currentAngle - startAngle;
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		PIDOutput output = new PIDOutput() {			
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				adjust = output;
			}
		};
		
		driveStraight = new PIDController(0.025, 0.0, 0.0, source, output);
		driveStraight.setAbsoluteTolerance(angleTolerance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	RobotMap.gearLightRing.set(Relay.Value.kForward);
    	targetDistance = 10.0f;
    	timeout = System.currentTimeMillis() + 1000 * 10;
    	startAngle = RobotMap.navXBoard.getAngle();

    	driveStraight.enable();

    	getPIDController().setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDistance = Robot.vision.getDistance();
    	currentAngle = RobotMap.navXBoard.getAngle();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() >= timeout){
    		return true;
    	}
        return currentDistance <= targetDistance;
        
    }

    // Called once after isFinished returns true
    protected void end() {
//		RobotMap.gearLightRing.set(Relay.Value.kOff);
    	driveStraight.disable();
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
		return Robot.vision.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(output, 0.0, adjust);
	}
}
