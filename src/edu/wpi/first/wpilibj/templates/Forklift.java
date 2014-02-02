/*




 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Sam
 */

public class Forklift {
    DigitalInput upLimit = new DigitalInput(1);
    DigitalInput midLimit = new DigitalInput(3);
    DigitalInput downLimit = new DigitalInput(6);
    Talon forkTalon = new Talon(7);
    boolean aboveMiddle;

    public void Forklift() {
       aboveMiddle = true;
        
    }
    /** this method makes the forklift move up if the upper limit switch is not being pushed.**/
    public void moveUp() {
        //upLimit is port 1
    if (downLimit.get()== false) {
        forkTalon.set(0);
    }
    else {
        forkTalon.set(-1);
    }
    }
/** this method makes the forklift move down if the bottom limit switch is not being pushed.**/
    public void moveDown(){
        //downLimit is port 6
    if (upLimit.get()== false) {
        forkTalon.set(0);
    }
    else {
        forkTalon.set(1);
    }
    }
    public void moveMiddle(){}
    
    public void aboveMiddle(){
    if (midLimit.get()== false) {
        forkTalon.set(0.5);
    }
    }
    public void belowMiddle(){
    if (midLimit.get()== false) {
        forkTalon.set(-1);
    }
    }
    /** this method makes the forklift stop moving.**/
    public void stop(){
    forkTalon.set(0);
    }
}