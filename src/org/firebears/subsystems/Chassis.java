package org.firebears.subsystems;

import org.firebears.RobotMap;
import org.firebears.commands.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * Subsystem for the drive train.
 */
public class Chassis extends Subsystem {

  private final MecanumDrive robotDrive = RobotMap.chassisrobotDrive;

  double driveX;
  double driveY;
  double driveRotation;

  public double getDriveX() {
    return driveX;
  }

  public double getDriveY() {
    return driveY;
  }

  public double getDriveRotation() {
    return driveRotation;
  }

  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }

  public void drive(double x, double y, double rotation) {
    double angle = 0;
    // angle = RobotMap.navXBoard.getAngle();
    driveX = x;
    driveY = y;
    driveRotation = rotation;
    robotDrive.driveCartesian(x * 1, y * -1, rotation * -1, angle);
  }

  /**
   * Note: double direction = Math.atan2(y, x) * 180 / Math.PI; double magnitude = Math.sqrt(x * x +
   * y * y);
   * 
   * @param magnitude The speed that the robot should drive in the range [-1.0..1.0].
   * @param direction The direction the robot should drive in degrees.
   * @param rotation The rate of rotation in the range [-1.0..1.0].
   */
  public void drivePolar(double magnitude, double direction, double rotation) {
    robotDrive.drivePolar(magnitude, direction, rotation);
  }

  public void stopDriving() {
    drive(0.0, 0.0, 0.0);
  }
}

