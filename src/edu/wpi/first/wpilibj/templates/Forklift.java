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
   // public void forkMove(){
        
        
  /*      if (downLimit.get())
        {
            if (midJoy.getTrigger())
            {
                forkTalon.set(.1);
            }
            else 
            {
                forkTalon.set(0);
            }
        } else if (midLimit.get()) {
            
            forkTalon.set(0);
            
            if (midJoy.getTrigger()) {
                
                forkTalon.set(.1);
                
            } 
            else if (leftJoy.getTrigger()) {
                forkTalon.set(-0.05);
            } 
            else {
                forkTalon.set(0);
            }
            }
        
        else if (upLimit.get()) 
        {  
            if(leftJoy.getTrigger())
            {
                forkTalon.set(-.05);
            }
            else 
            {
                forkTalon.set(0);
            } 
        } 
        else
        {if (leftJoy.getTrigger()) 
            {
                forkTalon.set(-.05);   
            }
            else
            {
               forkTalon.set(0); 
            }
         }*/
    
    public void forkMoveUp() {
        //upLimit is port 1
    if (downLimit.get()== false) {
        forkTalon.set(0);
    }
    else {
        forkTalon.set(-1);
    }
    }

    public void forkMoveDown(){
        //downLimit is port 6
    if (upLimit.get()== false) {
        forkTalon.set(0);
    }
    else {
        forkTalon.set(1);
    }
    }
    public void forkMoveMiddle(){}
    
    public void forkAboveMiddle(){
    if (midLimit.get()== false) {
        forkTalon.set(0.5);
    }
    }
    public void forkBelowMiddle(){
    if (midLimit.get()== false) {
        forkTalon.set(-1);
    }
    }
    public void stop(){
    forkTalon.set(0);
    }
}