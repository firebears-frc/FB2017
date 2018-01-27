package org.firebears.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

/**
 *
 */
public class TestVision extends Subsystem {

	NetworkTable visionTable = NetworkTableInstance.getDefault().getTable("Vision");
	final static double CAMERA_ANGLE = 10;
	
	public TestVision() {
	}
	
	public double getAngleX() {
		return visionTable.getEntry("AngleX").getDouble(0);
	}
	
	public double getAngleY() {
		return visionTable.getEntry("AngleY").getDouble(0);
	}
	
	// Find angle from height and angle
	static double findDistance(double height, double angle) {
		double distance = Math.tan((Math.PI / 180) * angle) * height;
		return distance;
	}
	
	public double getDistance() {
		SmartDashboard.putNumber("Distance Angle", 180 - (this.getAngleY() + 90 + 20 + CAMERA_ANGLE));
		return findDistance(16, (180 - (this.getAngleY() + 90 + 20 + CAMERA_ANGLE)));
	}
	
	public void testLatency() {
		double value = visionTable.getEntry("Test").getDouble(0);
		if (value == 1) {
			value = 2;
			visionTable.getEntry("Test").setDouble(value);
		}
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

