// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.firebears.commands.*;
import org.firebears.subsystems.*;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static org.firebears.RobotMap.boundAngle;

import com.ctre.CANTalon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Thread visionThread;


	private long count = 0;
	
	public static Command autonomousCommand;

	public static OI oi;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static Chassis chassis;
	public static Acquisition acquisition;
	public static Hopper hopper;
	public static Climber climber;
	public static SRX_PID sRX_PID;


	public static GearChute gearChute;
	public static Lights lights;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	// public static SRX_PID sRX_PID;

	public static Vision vision;
	public static SelectAuto selectAuto;
	private final LcdOverLay lcdol = new LcdOverLay();
	public static PitchWheelStop pitchwheelStop;
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		RobotMap.init();
		visionThread = new Thread(() -> {
			// Get the Axis camera from CameraServer
			AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
	
		
	
		
		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().startAutomaticCapture();
		
		


		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		chassis = new Chassis();
		acquisition = new Acquisition();
		hopper = new Hopper();
		climber = new Climber();
		gearChute = new GearChute();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		vision = new Vision();
		lights = new Lights();
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		
		selectAuto = new SelectAuto();
		pitchwheelStop = new PitchWheelStop();

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		autonomousCommand = new AutoGearMiddleCommand();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
		vision.setLightRingOff();	
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		count = 0;
		lcdol.disabled();
		vision.setLightRingOff();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		vision.update();
		
		lcdol.execute();
        
        if (Robot.oi.autoSwichButton.valueChanged()) {
			selectAuto.execute();
		}
		if ((count++) % 15 == 0) {
			lights.disabledMode();
		}
	}

	public void autonomousInit() {
		vision.setLightRingOn();
		count = 0;
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		vision.update();

		if ((count++) % 15 == 0) {
			lights.autonomousMode();
		}
	}

	public void teleopInit() {
		count = 0;
		lcdol.enabled();
		vision.setLightRingOff();
		
//		RobotMap.gearLightRing.set(Relay.Value.kForward);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		vision.update();
//		pitchwheelStop.execute();
		
		if(RobotMap.gearSensor.get() == true){
			SmartDashboard.putString("?", "True");
		}else{
			SmartDashboard.putString("?", "false");
		}

		if ((count++) % 15 == 0) {
			lights.teleopMode();

			if (RobotMap.DEBUG) {
				
				SmartDashboard.putString("gearInChute", gearChute.isGearInChute() ? "true" :"false");

				CANTalon talon2 = RobotMap.chassisfrontLeft;
				CANTalon talon3 = RobotMap.chassisfrontRight;
				CANTalon talon4 = RobotMap.chassisrearLeft;
				CANTalon talon5 = RobotMap.chassisrearRight;

				SmartDashboard.putNumber("EfrontLeft distT2", talon2.getEncPosition());
				SmartDashboard.putNumber("EfrontLeft rateT2", talon2.getEncVelocity());

				SmartDashboard.putNumber("EfrontRight distT3", talon3.getEncPosition());
				SmartDashboard.putNumber("EfrontRight rateT3", talon3.getEncVelocity());

				SmartDashboard.putNumber("ErearLeft distT4", talon4.getEncPosition());
				SmartDashboard.putNumber("ErearLeft rateT4", talon4.getEncVelocity());

				SmartDashboard.putNumber("ErearRight distT5", talon5.getEncPosition());
				SmartDashboard.putNumber("ErearRight rateT5", talon5.getEncVelocity());

				SmartDashboard.putNumber("rangeFinderDistance", gearChute.getRangeFinderDistance());
				SmartDashboard.putNumber("voltage", RobotMap.rangeFinder.getAverageVoltage());

				SmartDashboard.putNumber("DistanceInInches", Robot.gearChute.getRangeFinderDistance());

				SmartDashboard.putNumber("Corrected NavX Angle:", boundAngle((float) RobotMap.navXBoard.getAngle()));
				SmartDashboard.putNumber("NavX Angle:", RobotMap.navXBoard.getAngle());
//				SmartDashboard.putNumber("NavxYaw", RobotMap.navXBoard.getYaw());
//				SmartDashboard.putNumber("NavxPitch", RobotMap.navXBoard.getPitch());
//				SmartDashboard.putNumber("NavxRoll", RobotMap.navXBoard.getRoll());

				
				SmartDashboard.putNumber("Dumper Servo Pos:", RobotMap.dumperdumperServo.get());
				
				SmartDashboard.putBoolean("Vision On Target:", vision.isTargetVisible());
				
			}
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}