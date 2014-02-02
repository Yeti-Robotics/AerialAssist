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
    double modifier = 1d;
    Joystick leftJoy = new Joystick(2);
    Joystick rightJoy = new Joystick(1);
    boolean[] inverted = {false, false, true, true};
    DriveTrain yetiDrive = new DriveTrain(1, 2, 3, 4, inverted, 1);     
    DriverStationLCD driverStationLCD;
    AnalogChannel sonar = new AnalogChannel (3);
    Tracker tracker = new Tracker();
    Forklift forklift = new Forklift();

    
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

            if(rightJoy.getRawButton(2))
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
