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
    Talon forkTalon;
    boolean aboveMiddle;

    /**
     * Constructor class to initiate ports for the forklift
     * @param FORK_UP_LIMIT_POS Up limit switch port
     * @param FORK_MIDDLE_LIMIT_POS Middle limit switch port
     * @param FORK_DOWN_LIMIT_POS Down limit switch
     * @param FORK_TALON Talon controlling the forklift
     */
    public Forklift(final int FORK_UP_LIMIT_POS, final int FORK_MIDDLE_LIMIT_POS, final int FORK_DOWN_LIMIT_POS, final int FORK_TALON) {
       aboveMiddle = true;
       upLimit = new DigitalInput(FORK_UP_LIMIT_POS);
       midLimit = new DigitalInput(FORK_MIDDLE_LIMIT_POS);
       downLimit = new DigitalInput(FORK_DOWN_LIMIT_POS);
       forkTalon = new Talon(FORK_TALON);
    }
    /** this method makes the forklift move up if the upper limit switch is not being pushed.**/
    public void moveUp(double speed) {
        //upLimit is port 1
        
        if (upLimit.get()== false) {
            forkTalon.set(0);
        }
        else {
            forkTalon.set(speed);
        }
    }
    public void moveUpNoSwitch(double speed) {
        //upLimit is port 1
            forkTalon.set(speed);
    }
    public void moveDownNoSwitch(double speed) {
        //upLimit is port 1
            forkTalon.set(speed);
    }
/** this method makes the forklift move down if the bottom limit switch is not being pushed.**/
    public void moveDown(double speed){
        //downLimit is port 6
        if (downLimit.get()== false) {
            forkTalon.set(0);
        }
        else {
            forkTalon.set(speed);
        }
    }
    
    public boolean isDown(){
        return downLimit.get();
    }
    
    public boolean isUp(){
        return upLimit.get();
    }
    
    public void moveMiddle(double speed){}
    
    public void aboveMiddle(double speed){
        if (midLimit.get()== false) {
            forkTalon.set(speed);
        }
    }
    public void belowMiddle(double speed){
        if (midLimit.get()== false) {
            forkTalon.set(speed);
        }
    }
    /** this method makes the forklift stop moving.**/
    public void stop(){
        forkTalon.set(0);
    }
    
    /**
     * This method prints debug for the limit switches
     */
    public void debugLimit(){
        System.out.println("Down Limit Switch: \t" + downLimit.get() + "\n");
        System.out.println("Up Limit Switch: \t" + upLimit.get());
    }
}