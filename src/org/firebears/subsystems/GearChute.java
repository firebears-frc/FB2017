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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class GearChute extends Subsystem {
	
	/** Rangefinder volts per inch. */
    final double VOLT_DIST_RATIO = 0.0095; //5.084 Volts / 512 inch range 0.009929687

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogInput rangeFinder = RobotMap.rangeFinder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	
    }
    private double getRangeFinderVoltage() {
//    	return RobotMap.rangeFinder.getAverageVoltage();
    	return RobotMap.rangeFinder2.getAverageVoltage();
    }

    public double getRangeFinderDistance() {
//        double distanceInInches = getRangeFinderVoltage() / VOLT_DIST_RATIO;
        double distanceInInches = getRangeFinderVoltage() * 107.5;

        return distanceInInches;
    }
    
    public boolean isGearInChute(){
    	return RobotMap.gearSensor.get();
//    	if (RobotMap.gearSensor.get( ) == true) {
//    		return false;
//    	} else {
//    		return true;
//    	}
    }
}

