/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Amy
 */

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;


public class DriveTrain {
    RobotDrive drive;
    Gyro gyro;
    public static final int FRPORT = 1;
    public static final int FLPORT = 1;
    public static final int BRPORT = 1;
    public static final int BLPORT = 1;
    
    Talon frontRight = new Talon(FRPORT);
    Talon frontLeft = new Talon(FLPORT);
    Talon backRight = new Talon(BRPORT);
    Talon backLeft = new Talon(BLPORT);
    
       
    public DriveTrain(int frontLeftPort, int rearLeftPort, int frontRightPort, int rearRightPort, boolean[] inverted, int gyroPort) {
        drive = new RobotDrive(frontLeftPort, rearLeftPort, frontRightPort, rearRightPort);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, inverted[0]);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, inverted[1]);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, inverted[2]);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, inverted[3]);
        drive.setSafetyEnabled(true);
        gyro = new Gyro(gyroPort);
    }
    
    public void drive(double x, double y, double rotation) {
        drive.mecanumDrive_Cartesian(x, y, rotation, gyro.getAngle());
    }
   
    public void moveForward(double speed) {
 
        frontRight.set(speed);
        frontLeft.set(speed);
        backRight.set(speed);
        backLeft.set(speed);
     
    } 
    
    public void driveCustom(double fr, double fl, double br, double bl){
         
        frontRight.set(fr);
        frontLeft.set(fl);
        backRight.set(br);
        backLeft.set(bl);
               
        
        
        
    }           
}

