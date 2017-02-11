package org.firebears.subsystems;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Subsystem interface to the lights running on the Raspberry Pi. Communicates
 * to the pi through the Network Tables.
 */
public class Lights extends Subsystem {

	double range;

	static NetworkTable table;
	boolean isCelebrateMode = false;

	public Lights() {
		table = NetworkTable.getTable("lights");
		setStrip(STRIP_CHASSIS_FRONT, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_BACK, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_BOTTOM, ANIM_FIRE);
		setStrip(STRIP_SIGNAL, ANIM_FIRE);
	}

	public void initDefaultCommand() {
	}

	public static void setStrip(String stripName, String animationName) {
		table.putString(stripName, animationName);
	}

	public void setValue(String stripName, double value) {
		table.putNumber(stripName + ".value", value);
	}

	public void teleopMode() {

		String allianceColor = getAllianceColor();

		range = Robot.gearChute.getRangeFinderDistance();

		if (isCelebrateMode) {
			setStrip(Lights.STRIP_SIGNAL, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_BACK, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_FRONT, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_BOTTOM, Lights.ANIM_EXPLODE);
			return;
		}
		setValue(STRIP_SIGNAL, range);

		if (Robot.climber.isRunning()) {

		} else if (!Robot.climber.isRunning()) {
			if (Robot.vision.isTargetVisible()) {
				if (range <= 60) {
					setStrip(Lights.STRIP_SIGNAL, Lights.ANIM_RANGE);
				}
			} else if (!Robot.vision.isTargetVisible()) {
				if (Robot.gearChute.isGearInChute()) {

				}
				if (!Robot.acquisition.isNotRunning()) {
					if (Robot.acquisition.isRunningForward()) {
					}
					if (Robot.acquisition.isRunningBackward()) {

					}
				} else if (Robot.acquisition.isNotRunning()) {
					if (Robot.floor.isFloorHigh()) {

					}
					if (Robot.floor.isFloorLow()) {

					}
					if (Robot.floor.isGoingDown()) {

					}
					if (Robot.floor.isGoingUp()) {
					}
				}
			}
		}
	}

	public void autonomousMode() {
		setStrip(STRIP_CHASSIS_BOTTOM, ANIM_IGNITE);
	}

	public void disabledMode() {
		String anim = getAllianceColor();
		setStrip(Lights.STRIP_SIGNAL, anim);
		setStrip(Lights.STRIP_CHASSIS_BACK, anim);
		setStrip(Lights.STRIP_CHASSIS_FRONT, anim);
		setStrip(Lights.STRIP_CHASSIS_BOTTOM, anim);
	}

	public void celebrateMode(boolean celebrate) {
		isCelebrateMode = celebrate;
	}

	protected String getAllianceColor() {
		if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
			return Lights.ANIM_PULSING_BLUE;
		} else {
			return Lights.ANIM_PULSING_RED;
		}
	}

	// public void shootMode() {
	// setStrip(Lights.STRIP_CELEBRATE, Lights.ANIM_SPARK);
	// }

	// Constants for pixel strips
	public static final String STRIP_CHASSIS_FRONT = "strip_chassis_front";
	public static final String STRIP_CHASSIS_BACK = "strip_chassis_back";
	public static final String STRIP_CHASSIS_BOTTOM = "strip_chassis_bottom";
	public static final String STRIP_SIGNAL = "strip_signal";

	// Constants for animations
	public static final String ANIM_PULSING_GREEN = "ANIM_PULSING_GREEN";
	public static final String ANIM_PULSING_RED = "ANIM_PULSING_RED";
	public static final String ANIM_PULSING_BLUE = "ANIM_PULSING_BLUE";
	public static final String ANIM_MOVING_BLUE = "ANIM_MOVING_BLUE";
	public static final String ANIM_EXPLODING_R_W_B = "ANIM_EXPLODING_R_W_B";
	public static final String ANIM_FIRE = "ANIM_FIRE";
	public static final String ANIM_CRAZY = "ANIM_CRAZY";
	public static final String ANIM_BINARY = "ANIM_BINARY";
	public static final String ANIM_BULB = "ANIM_BULB";
	public static final String ANIM_CATERPILLAR = "ANIM_CATERPILLAR";
	public static final String ANIM_SPARK = "ANIM_SPARK";
	public static final String ANIM_THEATER = "ANIM_THEATER";
	public static final String ANIM_EXPLODE = "ANIM_EXPLODE";
	public static final String ANIM_RANGE = "ANIM_RANGE";
	public static final String ANIM_IGNITE = "ANIM_IGNITE";

}
