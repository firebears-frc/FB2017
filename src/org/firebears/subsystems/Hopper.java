package org.firebears.subsystems;

import org.firebears.RobotMap;
import org.firebears.util.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The hopper is made up of two separate components on the robot:
 * 	1. The floor ( cloth at the bottom of the hopper that loosens and tightens )
 * 	2. The dumper ( a device that moves up and down to prevent or allow release of fuel )
 */
public class Hopper extends Subsystem {
	
    private final CANTalon floorLift = RobotMap.floorfloorLift;
    private final Servo dumperServo = RobotMap.dumperdumperServo;
    
    private boolean floorGoingUp = false;
    private boolean floorGoingDown = false;

    final double MAX_SERVO_VALUE = 1.0;
	final double MIN_SERVO_VALUE = 0.0;
	
	public int floorpostion = 1;

    public void initDefaultCommand() {
    }
    
    public void goUp(double speed){
    	floorLift.set(speed);
    	floorGoingUp = true;
        floorGoingDown = false;
    }
    
    public void goDown(double speed){
    	floorLift.set(-speed);
    	floorGoingUp = false;
        floorGoingDown = true;
    }
    
    public void stopHopper(){
    	floorLift.set(0);
    	floorGoingUp = false;
    	floorGoingDown = false;
    }
    
    public double getFloorDirection(){
    	return floorLift.get();
    }
    
	public boolean isFloorHigh() {
		return false;
	}

	public boolean isFloorLow() {
		return false;
	}

	public boolean isRunning() {
		return false;
	}

	public boolean isGoingDown() {
		return floorGoingDown;
	}

	public boolean isGoingUp() {
		return floorGoingUp;
	}
	
	public void raiseDumperServo(){
		dumperServo.set(MAX_SERVO_VALUE);
	}
	public void lowerDumperServo(){
		dumperServo.set(MIN_SERVO_VALUE);
	}
}

