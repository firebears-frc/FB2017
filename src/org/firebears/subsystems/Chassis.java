// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.firebears.subsystems;

import org.firebears.RobotMap;
import org.firebears.commands.*;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon frontLeft = RobotMap.chassisfrontLeft;
    private final CANTalon frontRight = RobotMap.chassisfrontRight;
    private final CANTalon rearLeft = RobotMap.chassisrearLeft;
    private final CANTalon rearRight = RobotMap.chassisrearRight;
    private final RobotDrive robotDrive = RobotMap.chassisrobotDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    double driveX;
    public double getDriveX() {
		return driveX;
	}

	public double getDriveY() {
		return driveY;
	}

	public double getDriveRotation() {
		return driveRotation;
	}

	double driveY;
    double driveRotation;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double x, double y, double rotation) {
    	double angle = 0;
//    	angle = RobotMap.navXBoard.getAngle();
    	driveX = x;
    	driveY = y;
    	driveRotation = rotation;
    	robotDrive.mecanumDrive_Cartesian(x * -1, y * -1, rotation, angle);
    }
    
	/**
	 * Note:
	 *     double direction = Math.atan2(y, x) * 180 / Math.PI;
     *     double magnitude = Math.sqrt(x * x + y * y);
     * 
	 * @param magnitude The speed that the robot should drive in the range [-1.0..1.0].
	 * @param direction The direction the robot should drive in degrees.
	 * @param rotation The rate of rotation in the range [-1.0..1.0].
	 */
	public void drivePolar(double magnitude, double direction, double rotation) {
		robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
	}
	
	public void stopDriving(){
		drive(0.0, 0.0, 0.0);
	}
}

