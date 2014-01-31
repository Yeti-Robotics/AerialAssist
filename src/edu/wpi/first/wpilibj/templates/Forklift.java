/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 *
 * @author Sam
 */

public class Forklift {
    Jaguar speedController;
    DigitalInput upLimit;
    DigitalInput downLimit;
    DigitalInput midLimit;

    public void Forklift(int speedControllerPort, int upLimitPort, int downLimitPort, int midLimitPort) {
        speedController = new Jaguar(speedControllerPort);
        upLimit = new DigitalInput(upLimitPort);
        downLimit = new DigitalInput(downLimitPort);
        midLimit = new DigitalInput(midLimitPort);
    }
    public void move(){}
    public void moveUp(){}
    public void moveDown(){}
    public void moveMiddle(){}
}
