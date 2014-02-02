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
    DigitalInput upLimit;
    DigitalInput midLimit;
    DigitalInput downLimit;
    Talon forkTalon = new Talon(7);
    boolean aboveMiddle;

    public Forklift(final int FORK_UP_LIMIT_POS, final int FORK_MIDDLE_LIMIT_POS, final int FORK_DOWN_LIMIT_POS, final int FORK_TALON) {
       aboveMiddle = true;
       upLimit = new DigitalInput(FORK_UP_LIMIT_POS);
       midLimit = new DigitalInput(FORK_MIDDLE_LIMIT_POS);
       downLimit = new DigitalInput(FORK_DOWN_LIMIT_POS);
       forkTalon = new Talon(FORK_TALON);
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