/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class RobotTemplate extends SimpleRobot {
    
    //CATAPULT
    
    private static int UP_SPIKE_POS = 1;
    private static int DOWN_SPIKE_POS = 2;
    private static int CAT_LOWER_LIMIT_POS = 3;
    private static int CAT_MIDDLE_LIMIT_POS = 4;
    private static int CAT_LOADED_LIMIT_POS = 5;
    
    //FORKLIFT
    
    private static int FORK_UP_LIMIT_POS = 6;
    private static int FORK_MIDDLE_LIMIT_POS = 7;
    private static int FORK_DOWN_LIMIT_POS = 8;
    private static int FORK_TALON = 9;
        
    
    
    double modifier = 1d;
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

    
    public void robotInit() {
        leftJoy = new Joystick(2);
        rightJoy = new Joystick(1);
        shootJoy = new Joystick(3);
        yetiDrive = new DriveTrain(1, 3, 2, 4, inverted, 2);
        sonar = new AnalogChannel(3);
        catapult = new Catapult(UP_SPIKE_POS, DOWN_SPIKE_POS, CAT_LOWER_LIMIT_POS, CAT_MIDDLE_LIMIT_POS, CAT_LOADED_LIMIT_POS);
        forklift = new Forklift(FORK_UP_LIMIT_POS, FORK_MIDDLE_LIMIT_POS, FORK_DOWN_LIMIT_POS, FORK_TALON);

    }
    
    
    
    
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        while (tracker.trackY(5, sonar, .3) != 0)
        {
            yetiDrive.driveForward(tracker.trackY(5,sonar,.3));
        }
}

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        tracker = new Tracker();
        //forklift = new Forklift();
        //catapult = new Catapult(UP_SPIKE_POS,DOWN_SPIKE_POS,CAT_LOWER_LIMIT_POS,CAT_MIDDLE_LIMIT_POS,CAT_LOADED_LIMIT_POS);
        //forklift = new Forklift(FORK_UP_LIMIT_POS, FORK_MIDDLE_LIMIT_POS, FORK_DOWN_LIMIT_POS, FORK_TALON);
        driverStationLCD = DriverStationLCD.getInstance();
        while(isEnabled()) {                      
            if(leftJoy.getRawButton(3))
            {
                yetiDrive.driveForward(tracker.trackY(5,sonar,.3));
                System.out.println("track: " + tracker.trackY(5,sonar,.3));
                driverStationLCD.println(DriverStationLCD.Line.kUser1, 3, "tracking");
                
            }
            else
            {
                yetiDrive.drive(leftJoy.getX() * modifier, leftJoy.getY() * modifier, rightJoy.getX() * modifier);
            }

            /*if(rightJoy.getRawButton(2))
            {
                forklift.moveUp();
            }
            else if(leftJoy.getRawButton(3))
            {
                forklift.moveDown();
            }
            else if(rightJoy.getRawButton(3))
            {
                forklift.moveMiddle();
            }
            else
            {
                forklift.stop();
            }
            if(shootJoy.getRawButton(1))
            {
                catapult.armTop();
            }*/
            driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, "" + 10*sonar.getVoltage());
            driverStationLCD.updateLCD();
            Timer.delay(0.01);
            
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}