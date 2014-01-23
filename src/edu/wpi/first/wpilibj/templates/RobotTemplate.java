/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class RobotTemplate extends SimpleRobot {
    
    Jaguar frontLeft, frontRight, backLeft, backRight;
    Joystick leftJoy, rightJoy;
    
    public void RobotTemplate() {
    
        frontLeft = new Jaguar(1);
        frontRight = new Jaguar(2);
        backLeft = new Jaguar(3);
        backRight = new Jaguar(4);
        leftJoy = new Joystick(1);
        rightJoy = new Joystick(2);
        
        
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isEnabled()) {
            double leftY = leftJoy.getY();
            double rightY = rightJoy.getY();
            frontRight.set(rightY);
            frontLeft.set(leftY);
            backRight.set(rightY);
            backLeft.set(leftY);
            
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
