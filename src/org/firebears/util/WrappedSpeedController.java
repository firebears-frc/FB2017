package org.firebears.util;

import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * SpeedController class that wraps around another SpeedController.
 * This lets us intercept the values and display them on the SmartDashboard.
 */
public class WrappedSpeedController implements SpeedController {

	final SpeedController speedController;
	final String name;
	double motorValue;

	public WrappedSpeedController(SpeedController speedController, String controllerName) {
		this.speedController = speedController;
		this.name = controllerName;
	}

	@Override
	public void pidWrite(double output) {
		speedController.pidWrite(output);
	}

	@Override
	public double get() {
		return motorValue;
	}

	@Override
	public void set(double speed) {
		if (RobotMap.DEBUG) {
			SmartDashboard.putNumber("Controller." + name, speed);
		}
		speedController.set(speed);
		motorValue = speed;
	}

	@Override
	public void setInverted(boolean isInverted) {
		speedController.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return speedController.getInverted();
	}

	@Override
	public void disable() {
		speedController.disable();
	}

	@Override
	public void stopMotor() {
		speedController.stopMotor();
	}

}
