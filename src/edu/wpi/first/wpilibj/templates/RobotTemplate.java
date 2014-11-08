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

public class RobotTemplate extends SimpleRobot {
    
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
    
    
    double modifier = 0.4d;
    double leftX = 0;
    double leftY = 0;
    double rightX = 0;
    boolean isLinear = true;
    boolean toggleLights = false;
    
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
        int repeat = 0;
        yetiDrive.resetGyro();
        System.out.println("autonomous is running");
        if (forklift.isUp() == true){
            System.out.println("the first autonomous loop is selected");
            while (/*(tracker.trackY(7, sonar, .3) != 0 && */repeat < 10/*)*/ /*&& forklift.isDown()*/)
            {
                System.out.println("repeat:" + repeat);
                if (tracker.trackY(7, sonar, .3) == 0)
                {
                    repeat++;
                }
                else
                {
                    repeat = 0;
                }
                //System.out.println("the tracker while loop is running");
                yetiDrive.driveForward(tracker.trackY(7,sonar,.3));
                System.out.println(/*forklift.isDown() + */"\t" + sonar.getVoltage()*10);
                catapult.catDown();
                forklift.moveDown(-0.5);
                Timer.delay(.01);
            }
            catapult.catUp();
        }else{
            System.out.println("the second autonomous loop is selected");
            while (tracker.trackY(7, sonar, .3) != 0)
            {
                System.out.println("the tracker autonomous loop is running");
                yetiDrive.driveForward(tracker.trackY(7,sonar,.3));
                forklift.moveDown(-0.5);
                Timer.delay(.01);
            }
        }
    }


    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        driverStationLCD = DriverStationLCD.getInstance();
        while(isEnabled()) {
            //Tests the reel thing
//            if (shootJoy.getRawButton(10))
//            {
//                box.openBox();
//            }
//            else if (shootJoy.getRawButton(11))
//            {
//                box.closeBox();
//            }
//            else
//            {
//                box.stopBox();
//            }
            //Toggles the lights
            System.out.println("toggleLights is " + toggleLights);
            if (shootJoy.getRawButton(10) && toggleLights == false)
            {
                toggleLights = true;
            }
            if (shootJoy.getRawButton(10) && toggleLights == true)
            {
                toggleLights = false;
            }
            if (toggleLights == true)
            {
                box.openBox();
            }
            if(leftJoy.getRawButton(8))
            {
                yetiDrive.resetGyro();
            }
            //Turns on the air compressor
            if(shootJoy.getRawButton(9)){
                compressorSpike.set(Relay.Value.kForward);
            }
            else if(shootJoy.getRawButton(8))
            {
                compressorSpike.set(Relay.Value.kOff);
            }
            else if (!digitalCompressor.get())
            {
                compressorSpike.set(Relay.Value.kForward);
                System.out.println("Not Full");
            }
            else
            {
                compressorSpike.set(Relay.Value.kOff);
                System.out.println("Full");
            }
            //Displays distance from the ultrasonic sensor
            if(leftJoy.getRawButton(7))
            {
                yetiDrive.driveForward(tracker.trackY(7,sonar,.3));
                System.out.println("track: " + tracker.trackY(7,sonar,.3));
                driverStationLCD.println(DriverStationLCD.Line.kUser1, 3, "tracking");
                
            }
            else
            {
                //Puts deadzones on the joysticks
                if(Math.abs(leftJoy.getX())>.1)
                {
                    leftX = leftJoy.getX();
                }
                else
                {
                    leftX = 0;
                }
                
                if(Math.abs(leftJoy.getY())>.1)
                {
                    leftY = leftJoy.getY();
                }
                else
                {
                    leftY = 0;
                }
                
                if(Math.abs(rightJoy.getX())>.1)
                {
                    rightX = rightJoy.getX();
                }
                else
                {
                    rightX = 0;
                }
                
                
                /*                
                if(shootJoy.getRawButton(2))
                {
                    if(leftX < 0){
                        leftX = leftX*leftX*-1;
                    }
                    else{
                        leftX = leftX*leftX;
                     }
                    if(leftY < 0){
                        leftY = leftY*leftY*-1;
                    }
                    else{
                        leftY = leftY*leftY;    
                    }
                    if(rightX < 0){
                        rightX = rightX*rightX*-1;
                    }
                    else{
                        rightX = rightX*rightX; 
                    }
                }
                else if(shootJoy.getRawButton(3))
                {
                   
                    
                    //New type of drive code 
                    if(leftX < 0){
                        leftX = Math.sqrt(leftX*-1)*-1;
                    }
                    else{
                        leftX = Math.sqrt(leftX);
                     }
                    if(leftY < 0){
                        leftY = Math.sqrt(leftY*-1)*-1;
                    }
                    else{
                        leftY = Math.sqrt(leftY);    
                    }
                    if(rightX < 0){
                        rightX = Math.sqrt(rightX*-1)*-1;
                    }
                    else{
                        rightX = Math.sqrt(rightX); 
                    }
                }
                else if(shootJoy.getRawButton(4))
                {
                   leftX = leftX*leftX*leftX;
                    leftY = leftY*leftY*leftY;
                    rightX = rightX*rightX*rightX; 
                }*/
                /*if(isLinear == false)
                {
                    leftX = leftX*leftX;
                    leftY = leftY*leftY;
                    rightX = rightX*rightX;
                    
                    /*
                    leftX = Math.sqrt(leftX);
                    leftY = Math.sqrt(leftY);
                    rightX = Math.sqrt(rightX);
                    */
                    
                    /*
                    leftX = leftX*leftX*leftX;
                    leftY = leftY*leftY*leftY;
                    rightX = rightX*rightX*rightX;
                    */
                //}
                
                //The first yetiDrive line is used with deadzones, the second is used without deadzones. The line not in use is commented out
                yetiDrive.drive(leftX * modifier, leftY * modifier, rightX * modifier);
                //yetiDrive.drive(leftJoy.getX() * modifier, leftJoy.getY() * modifier, rightJoy.getX() * modifier);
                //Checks to see if the air tank is full 
                
            //Driver can control the catapult and forklift with buttons    
            }
            if(shootJoy.getRawButton(6))
            {
                catapult.shoot();
            }
            else if(shootJoy.getRawButton(7))
            {
                catapult.reload();
            }
            if(shootJoy.getRawButton(4))
            {
                catapult.openLatch();
            }
            else if(shootJoy.getRawButton(5))
            {
                catapult.closeLatch();
            }
            else if(shootJoy.getRawButton(2))
            {
                catapult.catUp();
            }
            else if(shootJoy.getRawButton(3))
            {
                catapult.catDown();
            }
            if(rightJoy.getRawButton(2))
            {
                forklift.moveUp(1.0);
            }
            else if(rightJoy.getRawButton(3))
            {
                forklift.moveDown(-.4);
            }
            /*else if(rightJoy.getRawButton(3))
            {
                forklift.moveMiddle();
            }*/
            else
            {
                forklift.stop();
            }
            driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, "" + ((int)(100*sonar.getVoltage()+5)/10.0)+"   ");
            driverStationLCD.updateLCD();
            
            
            
            Timer.delay(0.01);
            //System.out.println("is done " + catapult.isBottom());
            
            //Debug
            //forklift.debugLimit();
            
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    
    
    public void test() {
    
    }    
}
