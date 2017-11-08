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

import org.firebears.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.firebears.subsystems.*;
import org.firebears.util.RobotReport;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public JoystickButton acquisitionToggle;
	public JoystickButton climbRope;
	public JoystickButton floorGoUp;
	public JoystickButton floorGoDown;
    public JoystickButton autoGear;
    public JoystickButton flap;
    public JoystickButton visionToGear;
    public JoystickButton turnLightRingOn;
    public JoystickButton turnLightRingOff;
    public JoystickButton hopperGoUp;
    public JoystickButton hopperGoDown;
    public JoystickButton dumpBalls;
    public JoystickButton RunningTest;
	public Joystick joystick1;
	public Joystick joystick2;
	public DigitalButton autoSwichButton;

	public OI(RobotReport report) {

		

		joystick2 = new Joystick(1);
		report.addJoystick(1, "secondary joystick", joystick2);

		joystick1 = new Joystick(0);
		report.addJoystick(0, "drive's joystick", joystick1);
		
		//for putting the gear on autonomously, currently does nothing though
//		autoGear = new JoystickButton(joystick2, 12);
		
		//Buttons for Joystick
		
		RunningTest = new JoystickButton(joystick1, 11);
		RunningTestCommand runningTest = new RunningTestCommand();
		RunningTest.whenPressed(runningTest);
		report.addJoystickButton(0, 11, "Run system test", runningTest);
//		acquisitionToggle = new JoystickButton(joystick2, 8);
//		acquisitionToggle.whenPressed(new AcquireCommand());
//
//		climbRope = new JoystickButton(joystick2, 7);
//		climbRope.whileHeld(new ClimbCommand(1.0));
//		
//		flap = new JoystickButton(joystick2, 11);
//		flap.whenPressed(new DumperServoToggleCommand());
//		
//		visionToGear = new JoystickButton(joystick1, 12);
//		visionToGear.whenPressed(new VisionCommandGroup());
//		
//		turnLightRingOn = new JoystickButton(joystick2, 5);
//		turnLightRingOn.whenPressed(new VisionLightRingCommand(true));
//		
//		turnLightRingOff = new JoystickButton(joystick2, 6);
//		turnLightRingOff.whenPressed(new VisionLightRingCommand(false));
//		
//		hopperGoUp = new JoystickButton(joystick2, 10);
//		hopperGoUp.whileHeld(new GoUpCommand());
//		
//		hopperGoDown = new JoystickButton(joystick2, 9);
//		hopperGoDown.whileHeld(new GoDownCommand());
		
//		climbRope = new JoystickButton(joystick1, 10);
//		climbRope.whileHeld(new ClimbCommand(-1.0));

		autoSwichButton = new DigitalButton(0);
		SelectAuto autonomousSelectCommand = new SelectAuto();
		autoSwichButton.whenActive(autonomousSelectCommand);
        report.addDigitalIO(0, "autonomous select button", autonomousSelectCommand);
		
//		floorGoUp = new JoystickButton(joystick1, 11);
//		floorGoUp.whenPressed(new DumpCommand());

//		floorGoDown = new JoystickButton(joystick1, 12);
		
		//Driverstation Buttons
//		visionToGear = new JoystickButton(joystick2, 1);
//		visionToGear.whenPressed(new VisionCommandGroup());
		
		floorGoUp = new JoystickButton(joystick2, 1);
		DumpCommand dumpCommand = new DumpCommand(.75);
		floorGoUp.whileHeld(dumpCommand);
		report.addJoystickButton(1, 1, "Dump", dumpCommand);
		
		floorGoDown = new JoystickButton(joystick2, 5);
		LiftFloorCommand liftFloorCommand = new LiftFloorCommand(.5);
		floorGoDown.whileHeld(liftFloorCommand);
		report.addJoystickButton(1, 5, "Lift Floor", liftFloorCommand);
		
		acquisitionToggle = new JoystickButton(joystick2, 2);
		AcquireCommand acquireCommand = new AcquireCommand(true);
		acquisitionToggle.whenPressed(acquireCommand);
		acquisitionToggle.whenReleased(new AcquireCommand(false));
		report.addJoystickButton(1, 2, "Acquisition", acquireCommand);

		//CelebrationButton: Joystick 2, button 3
		
		climbRope = new JoystickButton(joystick2, 4);
		ClimbCommand climbCommand = new ClimbCommand(-1.0);
		climbRope.whileHeld(climbCommand);
		report.addJoystickButton(1, 4, "Climb", climbCommand);
		
//		dumpBalls = new JoystickButton(joystick2, 5);
//		dumpBalls.whenPressed(new DumpCommand());
		
		
		
		// SmartDashboard Buttons

		if (RobotMap.DEBUG) {
			SmartDashboard.putData("Turn:", new AutoRotate((float) 90.0));
			SmartDashboard.putData("Reset NavX:", new NavXReset());
			SmartDashboard.putData("VisionTurn:", new VisionRotate());
			SmartDashboard.putData("StartRecording", new StartMotionRecordCommand());
			SmartDashboard.putData("StopRecording", new StopMotionRecordCommand());
			SmartDashboard.putData("PlayRecording", new PlayRecordingCommand());
//			SmartDashboard.putData("PlayRecording(1)", new PlayRecordingCommand("recording/auto_gear_middle_1.csv"));
			SmartDashboard.putData("Vision Drive:", new VisionForwardIntoTarget());
			SmartDashboard.putData("Strafe Straight:", new StrafeStraightCommand());
			SmartDashboard.putData("Angle Adjust:", new VisionAngleAdjustCommand());
			SmartDashboard.putData("Vision Group Command (3 step):", new VisionCommandGroup());
			SmartDashboard.putData("Vision Hybrid Command", new VisionHybridCommand());
			SmartDashboard.putData("Drive Forward:", new MoveForwardInches(24,.1));
			SmartDashboard.putData("Servo Toggle:", new DumperServoToggleCommand());
			SmartDashboard.putData("Light ring on:", new VisionLightRingCommand(true));
			SmartDashboard.putData("Light ring off:", new VisionLightRingCommand(false));
			SmartDashboard.putData("Wait for Vision:", new WaitForVisionCommand());
			SmartDashboard.putData("GearWaitCommand", new WaitForGearCommand());
			SmartDashboard.putData("Autonomous Middle Test thing:", new AutoGearMiddleCommand());
			SmartDashboard.putData("Autonomous Left Test thing:", new AutoGearLeftCommand());
			SmartDashboard.putData("Autonomous Right Test thing:", new AutoGearRightCommand());
			SmartDashboard.putData("Drive To Gear:", new VisionForwardIntoTarget());
			SmartDashboard.putData("RunningTestCommand", runningTest);
			SmartDashboard.putData("MirrorPlay", new MirrorPlay());

			// SmartDashboard.putData("ClimbDown", new ClimbCommand(true));
			// SmartDashboard.putData("ClimbUp", new ClimbCommand(false));
		}
		SmartDashboard.putData("Commands", Scheduler.getInstance());
	}

	public Joystick getJoystick1() {
		return joystick1;
	}

	public Joystick getJoystick2() {
		return joystick2;
	}

}
