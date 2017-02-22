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

import org.firebears.util.LiquidCrystal;
import org.firebears.util.LiquidCrystal_roboRio;


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Ultrasonic;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;

import edu.wpi.first.wpilibj.Relay;

import com.kauailabs.navx.frc.AHRS;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final boolean DEBUG = true;
	public static final boolean CHASSIS_BRAKE_MODE = true;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon chassisfrontLeft;
    public static CANTalon chassisfrontRight;
    public static CANTalon chassisrearLeft;
    public static CANTalon chassisrearRight;
    public static RobotDrive chassisrobotDrive;
    public static CANTalon acquisitionacquisitionMotor;
    public static Servo dumperdumperServo;
    public static CANTalon floorfloorLift;
    public static CANTalon climberclimbMotor;
    public static Ultrasonic gearChuteultrasonic;
//    public static DigitalInput breakBeam;
    public static DigitalInput gearSensor;
    
    public static AnalogInput rangeFinder;
	public static LiquidCrystal lcd;
	
	public static CameraServer cameraServer;
	public static UsbCamera usbCamera0;
	public static UsbCamera usbCamera1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static Encoder encoderLeft;
	public static Encoder encoderRight;
	
	public static Relay gearLightRing;
	
	public static AHRS navXBoard;
	
	
	static double m_P = 3.0f;//1.25
	static double m_D = 0;
	static double m_ff = 0;
	static int m_izone = 256;
	static double m_I = 0;
	static double m_rampRate = 10;
	static int m_profile = 0;
	static int m_CountPerRev = 255;//****Magnetic
	
    public static void init() {



        
    	
    	
    	
//    	//START CLOSED LOOP PID
//    	
//    	//2 = Front Left
//        //3 = Front Right
//    	//4 = Rear Left
//    	//5 = Rear Right
//    	
    	
		chassisfrontLeft = new CANTalon(2);
		chassisfrontLeft.changeControlMode(CANTalon.TalonControlMode.Speed);
		chassisfrontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisfrontLeft.reverseSensor(false);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisfrontLeft.configNominalOutputVoltage(+0.0d, -0.0d);//Forward/reverse threshold
		chassisfrontLeft.configPeakOutputVoltage(+12.0d, -12.0d);
		chassisfrontLeft.setPID(m_P, m_I, m_D, m_ff, m_izone, m_rampRate, m_profile );
		chassisfrontLeft.configEncoderCodesPerRev(m_CountPerRev);//
		chassisfrontLeft.enableBrakeMode(CHASSIS_BRAKE_MODE);
		chassisfrontLeft.enable();
		LiveWindow.addActuator("Chassis", "frontLeft", chassisfrontLeft);
		
		chassisfrontRight = new CANTalon(3);
		chassisfrontRight.changeControlMode(CANTalon.TalonControlMode.Speed);
		chassisfrontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisfrontRight.reverseSensor(false);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisfrontRight.configNominalOutputVoltage(+0.0d, -0.0d);//Forward/reverse threshold
		chassisfrontRight.configPeakOutputVoltage(+12.0d, -12.0d);
		chassisfrontRight.setPID(m_P, m_I, m_D, m_ff, m_izone, m_rampRate, m_profile );
		chassisfrontRight.configEncoderCodesPerRev(m_CountPerRev);//
		chassisfrontRight.enableBrakeMode(CHASSIS_BRAKE_MODE);
		chassisfrontRight.enable();
		LiveWindow.addActuator("Chassis", "frontRight", chassisfrontRight);
		
		
		chassisrearLeft = new CANTalon(4);
		chassisrearLeft.changeControlMode(CANTalon.TalonControlMode.Speed);
		chassisrearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisrearLeft.reverseSensor(false);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisrearLeft.configNominalOutputVoltage(+0.0d, -0.0d);//Forward/reverse threshold
		chassisrearLeft.configPeakOutputVoltage(+12.0d, -12.0d);
		chassisrearLeft.setPID(m_P, m_I, m_D, m_ff, m_izone, m_rampRate, m_profile );
		chassisrearLeft.configEncoderCodesPerRev(m_CountPerRev);//
		chassisrearLeft.enableBrakeMode(CHASSIS_BRAKE_MODE);
		chassisrearLeft.enable();
		LiveWindow.addActuator("Chassis", "frontRight", chassisrearLeft);
		
				
		chassisrearRight = new CANTalon(5);
		chassisrearRight.changeControlMode(CANTalon.TalonControlMode.Speed);
		chassisrearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisrearRight.reverseSensor(false);//is this covered above with the sRX_PIDQuadratureEncoder1   ?????
		chassisrearRight.configNominalOutputVoltage(+0.0d, -0.0d);//Forward/reverse threshold
		chassisrearRight.configPeakOutputVoltage(+12.0d, -12.0d);
		chassisrearRight.setPID(m_P, m_I, m_D, m_ff, m_izone, m_rampRate, m_profile );
		chassisrearRight.configEncoderCodesPerRev(m_CountPerRev);//
		chassisrearRight.enableBrakeMode(CHASSIS_BRAKE_MODE);
		chassisrearRight.enable();
		LiveWindow.addActuator("Chassis", "rearRight", chassisrearRight);
		

			
		chassisrobotDrive = new RobotDrive(chassisfrontLeft, chassisrearLeft,
				chassisfrontRight, chassisrearRight);
		
    	chassisrobotDrive.setSafetyEnabled(true);
        chassisrobotDrive.setExpiration(0.1);
        chassisrobotDrive.setSensitivity(0.0);
        chassisrobotDrive.setMaxOutput(400);       		
        chassisrobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        chassisrobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		
    	
//		//END CLOSED LOOP PID
        
//		try {
//			cameraServer = CameraServer.getInstance();
//			usbCamera1 = cameraServer.startAutomaticCapture(1);
//			usbCamera0 = cameraServer.startAutomaticCapture(0);
//		}catch(Error e) {
//			System.err.println("Couldn't Open Cameras");
//		}


		
        
        


		
		
		
		//Start Open Loop Drive code
		
		
//		chassisfrontLeft = new CANTalon(2);
//        LiveWindow.addActuator("Chassis", "frontLeft", chassisfrontLeft);
//        
//        chassisfrontRight = new CANTalon(3);
//        LiveWindow.addActuator("Chassis", "frontRight", chassisfrontRight);
//        
//        chassisrearLeft = new CANTalon(4);
//        LiveWindow.addActuator("Chassis", "rearLeft", chassisrearLeft);
//        
//        chassisrearRight = new CANTalon(5);
//        LiveWindow.addActuator("Chassis", "rearRight", chassisrearRight);
//        
//        chassisrobotDrive = new RobotDrive(chassisfrontLeft, chassisrearLeft,
//              chassisfrontRight, chassisrearRight);
//        
//        chassisrobotDrive.setSafetyEnabled(true);
//        chassisrobotDrive.setExpiration(0.1);
//        chassisrobotDrive.setSensitivity(0.5);
//        chassisrobotDrive.setMaxOutput(1.0);
//        chassisrobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
//        chassisrobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
//        
        
        //END Open loop code
        
        
        
        
        
        
        
        //Auto generated stuff
            	
        dumperdumperServo = new Servo(0);
        LiveWindow.addActuator("Dumper", "dumperServo", dumperdumperServo);
        
        floorfloorLift = new CANTalon(12);
        LiveWindow.addActuator("Floor", "floorLift", floorfloorLift);
        
        climberclimbMotor = new CANTalon(11);
        LiveWindow.addActuator("Climber", "climbMotor", climberclimbMotor);
        
    	acquisitionacquisitionMotor = new CANTalon(14);
        LiveWindow.addActuator("Acquisition", "acquisitionMotor", acquisitionacquisitionMotor);
        
        gearSensor = new DigitalInput(1);

        //
        
        rangeFinder = new AnalogInput(0);
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


        lcd = new LiquidCrystal_roboRio(0x3f); //0x3f
		lcd.begin(20, 4);
		lcd.clear();
		
//		breakBeam = new DigitalInput(1);

		
        
        gearLightRing = new Relay(0, Relay.Direction.kForward);
        
		try {
			navXBoard = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}

    }
    
    /**
     * @return current NavXBoard angle wrapped to the range -180 to 180.
     */
    public static double getNavXAngle() {
    	return boundAngle(navXBoard.getAngle());
    }
    
    /**
     * @return the angle wrapped into the range -180 to 180.
     */
    public static double boundAngle(double angle) {
		while (angle > 180) angle -= 360;
		while (angle < -180) angle += 360;
		return angle;
	}
}
