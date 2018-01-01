package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * TODO: Remove unused command.
 */		
		// TODO Make it work when motor turns in different direction.
public class StrafeInches extends PIDCommand {

	double moveDistance;
	double startDistance;
	double targetDistance;
	double timeout;
	protected final double SPEED;
	protected final double tolerance = 3.00;
	double incoder;
	double in;

	public StrafeInches(double inches, double speed) {
		super(0.5, 0.0, 0.0);				
		requires(Robot.chassis);

		SPEED = speed;
		moveDistance = inches;
		getPIDController().setAbsoluteTolerance(tolerance);
	}

	public double toInches(double EncoderValue) {
		return EncoderValue / 53.55;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(SPEED < 0){
			incoder = RobotMap._chassisfrontLeft.getSelectedSensorPosition(RobotMap.pidIdx);
			in = -1;
		}else{
			incoder = RobotMap._chassisrearLeft.getSelectedSensorPosition(RobotMap.pidIdx);
			in = 1;
		}

		timeout = System.currentTimeMillis() + 1000 * 5;
		startDistance = toInches(incoder);
		targetDistance = startDistance - moveDistance;
		getPIDController().setSetpoint(targetDistance);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (System.currentTimeMillis() >= timeout) {
			System.out.println("Strafe: Timeout");
			return true;
		}
		if (Math.abs(toInches(RobotMap._chassisrearLeft.getSelectedSensorPosition(RobotMap.pidIdx)) - targetDistance) < tolerance) {
			System.out.println("Strafe: Within Tolerance");
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.stopDriving();
//		System.out.println("Vision Forward Start At " + (toInches(RobotMap.chassisfrontLeft.getEncPosition()) - targetDistance) + " degrees off");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return toInches(RobotMap._chassisrearLeft.getSelectedSensorPosition(RobotMap.pidIdx));
	}

	@Override
	protected void usePIDOutput(double output) {
		output = Math.max(-SPEED, Math.min(output, SPEED));
		Robot.chassis.drive(output*in, 0.0, 0.03*in);
	}
}
