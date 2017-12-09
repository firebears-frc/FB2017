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
import org.firebears.commands.JoyCon;

import org.firebears.util.CANTalon;

import com.ctre.phoenix.MotorControl.ControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc2846.TalonPID.OI;
//import org.usfirst.frc2846.TalonPID.RobotMap;
//import org.usfirst.frc2846.TalonPID.commands.*;
//
//import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
//import edu.wpi.first.wpilibj.CounterBase.EncodingType;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class SRX_PID extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	
	double max;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Encoder quadratureEncoder1 = RobotMap.encoderRight;//sRX_PIDQuadratureEncoder1;
    private final CANTalon cANTalon1 = RobotMap.chassisfrontRight;
    //private final PIDController pIDController1 = RobotMap.sRX_PIDPIDController1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public SRX_PID()
    {
    	
    }


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    	setDefaultCommand(new JoyCon());
    	
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSetpointRPS(double speed)
    {
    	SmartDashboard.putNumber("SubJoy", speed);
    	double targetSpeed = speed * 650d;//pulses per 100ms?? ie 1700 or 4000rpm, which way is it??? Someone sneak in a conversion??
    	cANTalon1.changeControlMode(ControlMode.Velocity);
    	SmartDashboard.putNumber("SubJoyTgt", targetSpeed);
    	//SmartDashboard.putNumber("Speed = ",cANTalon1. )
    	cANTalon1.set(targetSpeed);
    }

}

