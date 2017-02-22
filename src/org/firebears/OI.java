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

import org.firebears.subsystems.*;

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

	public Joystick joystick1;
	public Joystick joystick2;
	public DigitalButton autoSwichButton;

	public OI() {

		

		joystick2 = new Joystick(1);

		joystick1 = new Joystick(0);
		
		//for putting the gear on autonomously, currently does nothing though
//		autoGear = new JoystickButton(joystick2, 12);

		acquisitionToggle = new JoystickButton(joystick2, 9);
		acquisitionToggle.whenPressed(new AcquireCommand());

		climbRope = new JoystickButton(joystick2, 7);
		climbRope.whileHeld(new ClimbCommand(1.0));
		
		flap = new JoystickButton(joystick2, 11);
		flap.whenPressed(new DumperServoToggleCommand());
		
//		climbRope = new JoystickButton(joystick1, 10);
//		climbRope.whileHeld(new ClimbCommand(-1.0));

//		autoSwichButton = new DigitalButton(0);
//		autoSwichButton.whenActive(new SelectAuto());
		
//		floorGoUp = new JoystickButton(joystick1, 11);
//		floorGoUp.whenPressed(new DumpCommand());

//		floorGoDown = new JoystickButton(joystick1, 12);
		

		// SmartDashboard Buttons

		if (RobotMap.DEBUG) {
			SmartDashboard.putData("Turn:", new AutoRotate((float) 90.0));
			SmartDashboard.putData("Reset NavX:", new NavXReset());
			SmartDashboard.putData("Original VisionTurn:", new VisionRotate(false));
			SmartDashboard.putData("Bar Ratio VisionTurn:", new VisionRotate(true));
			SmartDashboard.putData("StartRecording", new StartMotionRecordCommand());
			SmartDashboard.putData("StopRecording", new StopMotionRecordCommand());
			SmartDashboard.putData("PlayRecording", new PlayRecordingCommand());
//			SmartDashboard.putData("PlayRecording(1)", new PlayRecordingCommand("recording/auto_gear_middle_1.csv"));
			SmartDashboard.putData("Vision Drive:", new VisionBackIntoTarget());
			SmartDashboard.putData("Strafe (hopefully) Straight:", new StrafeStraightCommand());
			SmartDashboard.putData("Angle Adjust:", new VisionAngleAdjustCommand());
			SmartDashboard.putData("Vision Group Command (3 step):", new VisionCommandGroup());
			SmartDashboard.putData("Vision Hybrid Command", new VisionHybridCommand());
			SmartDashboard.putData("Drive Forward:", new MoveForwardInches(10.0));
			SmartDashboard.putData("Servo Toggle:", new DumperServoToggleCommand());
			SmartDashboard.putData("Light ring on:", new VisionLightRingCommand(true));
			SmartDashboard.putData("Light ring off:", new VisionLightRingCommand(false));
			SmartDashboard.putData("Wait for Vision:", new WaitForVisionCommand());
			SmartDashboard.putData("?2", new StrafeInches(12, 0.5));
			// SmartDashboard.putData("ClimbDown", new ClimbCommand(true));
			// SmartDashboard.putData("ClimbUp", new ClimbCommand(false));
		}
	}

	public Joystick getJoystick1() {
		return joystick1;
	}

	public Joystick getJoystick2() {
		return joystick2;
	}

}
