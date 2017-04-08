package org.firebears.commands;

import org.firebears.Robot;
import org.firebears.RobotMap;
import static org.firebears.RobotMap.boundAngle;
import static org.firebears.RobotMap.getNavXAngle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *  Drive to within 12 inches of the wall, correcting angle based on NavX.
 */
public class VisionForwardIntoTarget extends PIDCommand {

	long timeout;
	double startAngle;
	double currentAngle;
	double tolerance = 2.5;

	public VisionForwardIntoTarget() {
		super(0.025, 0.0, 0.0);
		requires(Robot.chassis);

		getPIDController().setInputRange(-180.0, 180.0);
		getPIDController().setAbsoluteTolerance(tolerance);
		getPIDController().setContinuous();
	}

	public double getAngleDifference() {
		return boundAngle(getNavXAngle() - startAngle);
	}

	protected void initialize() {
		timeout = System.currentTimeMillis() + 1000 * 6;
		startAngle = getNavXAngle() /* + ( Robot.vision.getTilt() * 4 ) */;
		getPIDController().setSetpoint(0.0);
	}

	protected void execute() {
		currentAngle = getNavXAngle();
	}

	protected boolean isFinished() {
		if (System.currentTimeMillis() >= timeout) {
			return true;
		}

		if (Robot.gearChute.getRangeFinderDistance() < 8) {
			return true;
		}
		return false;
	}

	protected void end() {
		Robot.chassis.stopDriving();
	}

	protected void interrupted() {
		end();
	}

	@Override
	protected double returnPIDInput() {
		return getAngleDifference();
	}

	@Override
	protected void usePIDOutput(double output) {
		double speed = (Robot.gearChute.getRangeFinderDistance() < 24) ? -0.10 : -0.30;
		Robot.chassis.drive(0.0, speed, output);
	}
}
