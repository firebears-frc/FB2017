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

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.firebears.commands.*;
import org.firebears.subsystems.*;
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
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
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

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		autonomousCommand = new AutonomousCommand();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		count = 0;
		lcdol.disabled();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		lcdol.execute();
        
        if (Robot.oi.autoSwichButton.valueChanged()) {
			selectAuto.execute();
		}
		if ((count++) % 15 == 0) {
			lights.disabledMode();
		}
	}

	public void autonomousInit() {
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

		if ((count++) % 15 == 0) {
			lights.autonomousMode();
		}
	}

	public void teleopInit() {
		count = 0;
		lcdol.enabled();
		
		

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

		if ((count++) % 15 == 0) {
			lights.teleopMode();

			if (RobotMap.DEBUG) {

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
				
				SmartDashboard.putNumber("Dumper Servo Pos:", RobotMap.dumperdumperServo.get());
				
				
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