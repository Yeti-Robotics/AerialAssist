/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/*
    BUTTONS ON JOYSTICKS
    Compressor on: joy3, button 9
    Compressor off: joy3, button 8
    Box open: joy3, button 10
    Box close: joy3, button 11
    Catapult shoot: joy3, button 6
    Catapult reload: joy3, button 7
    Catapult openLatch: joy3, button 4
    Catapult closeLatch: joy3, button 5
    Catapult catUp: joy3, button 2
    Catapult catDown: joy3, button 3
    Fork up: joy2, button 2
    Fork down: joy2, button 3
    Drive froward for testing purposes: joy1, button 7
*/

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class RobotTemplateTest extends SimpleRobot {
    
    //CATAPULT
    
    private static int LEFT_PISTON_ARM_POS= 1;
    private static int LEFT_PISTON_FIRE_POS= 2;
    private static int RIGHT_PISTON_ARM_POS= 3;
    private static int RIGHT_PISTON_FIRE_POS= 4;
    private static int LATCH_OPEN_POS= 6;
    private static int LATCH_CLOSED_POS= 5;
    //private static int DOWN_SPIKE_POS = 2;
    //private static int CAT_LOWER_LIMIT_POS = 3;
    //private static int CAT_MIDDLE_LIMIT_POS = 4;
    //private static int CAT_LOADED_LIMIT_POS = 5;
    
    //FORKLIFT
    
    private static int FORK_UP_LIMIT_POS = 2;
    private static int FORK_MIDDLE_LIMIT_POS = 3;
    private static int FORK_DOWN_LIMIT_POS = 1;
    private static int FORK_TALON = 5;
        
    //COMPRESSOR
    
    public static int COMPRESSOR_RELAY_POS = 1; //RO
    public static int DIGITAL_COMPRESSOR_POS = 4;//di
    public static int REEL_RELAY_POS = 5;
    
    
    double modifier = 1d;
    double leftX = 0;
    double leftY = 0;
    double rightX = 0;
    boolean isLinear = true;
    
    Joystick leftJoy;
    Joystick rightJoy;
    Joystick shootJoy;
    boolean[] inverted = {false, false, true, true};
    DriveTrain yetiDrive;     
    DriverStationLCD driverStationLCD;
    AnalogChannel sonar;
    Tracker tracker;
    Forklift forklift;
    Catapult catapult;
    Box box;
    
    private Relay reelSpike = new Relay(REEL_RELAY_POS);

    private Relay compressorSpike = new Relay(COMPRESSOR_RELAY_POS);
    private DigitalInput digitalCompressor = new DigitalInput(DIGITAL_COMPRESSOR_POS);
    
    public void robotInit() {
        leftJoy = new Joystick(2);
        rightJoy = new Joystick(1);
        shootJoy = new Joystick(3);
        yetiDrive = new DriveTrain(1, 3, 2, 4, inverted, 2);
        sonar = new AnalogChannel(3);
        catapult = new Catapult(LEFT_PISTON_ARM_POS, LEFT_PISTON_FIRE_POS, RIGHT_PISTON_ARM_POS, RIGHT_PISTON_FIRE_POS, LATCH_OPEN_POS, LATCH_CLOSED_POS, 5);
        forklift = new Forklift(FORK_UP_LIMIT_POS, FORK_MIDDLE_LIMIT_POS, FORK_DOWN_LIMIT_POS, FORK_TALON);
        tracker = new Tracker();
        box = new Box(6);
        
    }
    
    
    
    
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        yetiDrive.resetGyro();
        if (forklift.isUp() == true){
            
        }else{
        
        }
    }


    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        driverStationLCD = DriverStationLCD.getInstance();
        while(isEnabled()) {
            
            if(leftJoy.getRawButton(1)){
                yetiDrive.driveForward(.5);
            }else if(rightJoy.getRawButton(1)){
                
            }
        }
        System.out.println(yetiDrive.gyro.getAngle());
        driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, "" + yetiDrive.gyro.getAngle() + "");
        driverStationLCD.updateLCD();
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}