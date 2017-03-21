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
		setStrip(STRIP_CHASSIS_FRONT1, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_BACK1, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_BOTTOM1, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_FRONT2, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_BACK2, ANIM_FIRE);
		setStrip(STRIP_CHASSIS_BOTTOM2, ANIM_FIRE);
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
			setStrip(Lights.STRIP_CHASSIS_BACK1, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_FRONT1, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_BOTTOM1, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_BACK2, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_FRONT2, Lights.ANIM_EXPLODE);
			setStrip(Lights.STRIP_CHASSIS_BOTTOM2, Lights.ANIM_EXPLODE);
			return;
		}
		setValue(STRIP_SIGNAL, range);

		if (Robot.climber.isRunning()) {
			setStrip(Lights.STRIP_SIGNAL, Lights.ANIM_EXPLODING_R_W_B);
			setStrip(Lights.STRIP_CHASSIS_BACK1, Lights.ANIM_EXPLODING_R_W_B);
			setStrip(Lights.STRIP_CHASSIS_FRONT1, Lights.ANIM_EXPLODING_R_W_B);
			setStrip(Lights.STRIP_CHASSIS_BOTTOM1, Lights.ANIM_EXPLODING_R_W_B);
			setStrip(Lights.STRIP_CHASSIS_BACK2, Lights.ANIM_EXPLODING_R_W_B);
			setStrip(Lights.STRIP_CHASSIS_FRONT2, Lights.ANIM_EXPLODING_R_W_B);
			setStrip(Lights.STRIP_CHASSIS_BOTTOM2, Lights.ANIM_EXPLODING_R_W_B);
		} else {
//			 if (Robot.vision.isLightRingOn()) { 
			if (Robot.gearChute.isGearInChute()) {
				setStrip(Lights.STRIP_CHASSIS_BACK1, Lights.ANIM_FAIL);
				setStrip(Lights.STRIP_CHASSIS_BACK2, Lights.ANIM_FAIL);
			} else if (Robot.vision.isTargetVisible()) {
				if (range <= 20) {
					setStrip(Lights.STRIP_SIGNAL, Lights.ANIM_RANGE);
					setStrip(Lights.STRIP_CHASSIS_BACK1, Lights.ANIM_RANGE);
				}
			} else

			if (Robot.gearChute.isGearInChute()) {
				setStrip(Lights.STRIP_CHASSIS_BACK1, Lights.ANIM_SUCCESS);
				setStrip(Lights.STRIP_CHASSIS_BACK2, Lights.ANIM_SUCCESS);
			}
		}
		if (!Robot.acquisition.isNotRunning()) {
			if (Robot.acquisition.isRunningForward()) {
				setStrip(Lights.STRIP_CHASSIS_FRONT1, Lights.ANIM_SWEEPERFORWARDS);
				setStrip(Lights.STRIP_CHASSIS_FRONT2, Lights.ANIM_SWEEPERFORWARDS);
			}
			if (Robot.acquisition.isRunningBackward()) {
				setStrip(Lights.STRIP_CHASSIS_FRONT1, Lights.ANIM_SWEEPERBACKWARDS);
				setStrip(Lights.STRIP_CHASSIS_FRONT2, Lights.ANIM_SWEEPERBACKWARDS);
			}
		} else {
			if (Robot.hopper.isFloorHigh()) {
				setStrip(Lights.STRIP_CHASSIS_FRONT1, Lights.ANIM_HIGH);
				setStrip(Lights.STRIP_CHASSIS_FRONT2, Lights.ANIM_HIGH);
			}
			if (Robot.hopper.isFloorLow()) {
				setStrip(Lights.STRIP_CHASSIS_FRONT1, Lights.ANIM_LOW);
				setStrip(Lights.STRIP_CHASSIS_FRONT2, Lights.ANIM_LOW);
			}
			if (Robot.hopper.isGoingDown()) {

			}
			if (Robot.hopper.isGoingUp()) {

			}
		}
	}

	public void autonomousMode() {
		setStrip(STRIP_CHASSIS_BOTTOM1, ANIM_IGNITE);
		setStrip(STRIP_CHASSIS_BOTTOM2, ANIM_IGNITE);
	}

	public void disabledMode() {
		String anim = getAllianceColor();
		setStrip(Lights.STRIP_SIGNAL, anim);
		setStrip(Lights.STRIP_CHASSIS_BACK1, anim);
		setStrip(Lights.STRIP_CHASSIS_FRONT1, anim);
		setStrip(Lights.STRIP_CHASSIS_BOTTOM1, anim);
		setStrip(Lights.STRIP_CHASSIS_BACK2, anim);
		setStrip(Lights.STRIP_CHASSIS_FRONT2, anim);
		setStrip(Lights.STRIP_CHASSIS_BOTTOM2, anim);
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

	// Constants for pixel strips
	public static final String STRIP_CHASSIS_FRONT1 = "strip_chassis_front1";
	public static final String STRIP_CHASSIS_FRONT2 = "strip_chassis_front2";
	public static final String STRIP_CHASSIS_BACK1 = "strip_chassis_back1";
	public static final String STRIP_CHASSIS_BACK2 = "strip_chassis_back2";
	public static final String STRIP_CHASSIS_BOTTOM1 = "strip_chassis_bottom1";
	public static final String STRIP_CHASSIS_BOTTOM2 = "strip_chassis_bottom2";
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
	public static final String ANIM_SWEEPERFORWARDS = "ANIM_SWEEPERFORWARDS";
	public static final String ANIM_SWEEPERBACKWARDS = "ANIM_SWEEPERBACKWARDS";
	public static final String ANIM_FAIL = "ANIM_FAIL";
	public static final String ANIM_SUCCESS = "ANIM_SUCCESS";
	public static final String ANIM_LOW = "ANIM_LOW";
	public static final String ANIM_HIGH = "ANIM_HIGH";
}
