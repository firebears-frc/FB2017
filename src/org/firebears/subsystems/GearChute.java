package org.firebears.subsystems;

import org.firebears.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the detection of the gear in the chute.
 */
public class GearChute extends Subsystem {
	
	/** Rangefinder volts per inch. */
    final double VOLT_DIST_RATIO = 0.0095; //5.084 Volts / 512 inch range 0.009929687

    private final AnalogInput rangeFinder = RobotMap.rangeFinder;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    private double getRangeFinderVoltage() {
    	return RobotMap.rangeFinder.getAverageVoltage();
//    	return RobotMap.rangeFinder2.getAverageVoltage();
    }

    public double getRangeFinderDistance() {
//        double distanceInInches = getRangeFinderVoltage() *110.5;// / VOLT_DIST_RATIO;
        double distanceInInches = getRangeFinderVoltage() * 107.5;

        return distanceInInches;
    }
    
    public boolean isGearInChute(){
    	return !RobotMap.gearSensor.get();
//    	if (RobotMap.gearSensor.get( ) == true) {
//    		return false;
//    	} else {
//    		return true;
//    	}
    }
}

