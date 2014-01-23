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


public class DriveTrain {
    RobotDrive drive;
    Gyro gyro;
    
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
   
    public void frontRight() {
     
        
        
    }
    
    public void frontLeft() {
        
        
        
    }   
    
    public void backRight() {
        
        
        
    }
    
    public void backLeft() {
        
        
        
    }
   
    public void moveForward(int speed) {
        
    } 
}