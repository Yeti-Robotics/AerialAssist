/*
 * This class controls the shooting arm on the roboto
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.Solenoid;

public class Catapult {
    
    Solenoid solenoid1;
    Solenoid solenoid2;
    DigitalInput armDownLimit; 
    
    
    
    public void Catapult(int sol1, int sol2, int limitSwitchPort){
        solenoid1 = new Solenoid(sol1);
        solenoid2 = new Solenoid(sol2);
        armDownLimit = new DigitalInput(limitSwitchPort);
    }
    
    public boolean isDown(){
        boolean isDown = false;
        return isDown;
    }
    
      public boolean isLoaded(){
        boolean loadValue = false; 
        return loadValue;
    }
      
    public void shootHigh(){
    
    }
    
    public void shootLow(){
    
    }
}
