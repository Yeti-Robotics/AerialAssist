/*
 * This class controls the shooting arm on the roobut
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Catapult {
        
    //Solenoid upSol;
    //Solenoid downSpike;
    DoubleSolenoid leftPiston;
    DoubleSolenoid rightPiston;
    DoubleSolenoid latchPiston;
    DigitalInput armDownLimit; 
    DigitalInput armLoadedLimit;
    DigitalInput armMiddleLimit;
    Relay upSpike;
    Relay downSpike;
    Timer timer;
    boolean isOpen;
        
    /**
     * This is a constructor method to initialize ports given into the method through RobotTemplate.java
     * @param SPI1 port for the up spike of the catapult solenoid
     * @param SPI2 port for the down spike of the catapult solenoid
     * @param LSPDOWN port for the isDown limit switch
     * @param LSPLOADED port for the isLoaded limit switch
     * @param LSPMIDDLE port for the armBottomMiddle limit switch on the pistons
     */
    public Catapult(int leftArm,int leftFire,int rightArm, int rightFire, int latchOpen, int latchClose, int armDownLimitPos){
        
        //upSol = new Solenoid(sol1);
        //downSpike = new Solenoid(sol2);
        //upSpike = new Relay(UP_SPIKE_POS); //If upSol is set to Relay.Value.kOn and downSpike is set to Relay.Value.kOff then the arm will go up
        //downSpike = new Relay(DOWN_SPIKE_POS); //If downSpike is set to Relay.Value.kOn and upSol is set to Relay.Value.kOff then the arm will go down
        //armDownLimit = new DigitalInput(CAT_LOWER_LIMIT_POS);     
        //armLoadedLimit = new DigitalInput(CAT_LOADED_LIMIT_POS);
        //armMiddleLimit = new DigitalInput(CAT_MIDDLE_LIMIT_POS);
        leftPiston = new DoubleSolenoid(leftArm,leftFire);
        rightPiston = new DoubleSolenoid(rightArm,rightFire);
        latchPiston = new DoubleSolenoid(latchOpen,latchClose);
        timer = new Timer();
        armDownLimit = new DigitalInput(armDownLimitPos);
    }
    
    /**
     * This method finds if the arm is down or not and returns it as a boolean
     */
    public boolean isDown(){
        boolean downValue;
        if (armDownLimit.get() == true){
            downValue = true;
        }else{
            downValue = false;
        } 
        return downValue;
    }
      
    /**
     * This method finds if the ball is loaded into the arm and returns it as a boolean, may not be used
     */
    public boolean isLoaded(){
        boolean loadValue;
        if (armLoadedLimit.get() == true){
            loadValue = true;
        }
        else{
            loadValue = false;
        }
        return loadValue;
    }
    
     /*
     * This method engages the latch, brings the catapult up, and disengages the latch
     */
    public void shoot(){
        catUp();
        timer.delay(2.5);                 
        openLatch(); 
        isOpen = true;
        System.out.println("shoot method called");
    }
    
    /**
     * This method brings the catapult down and engages the latch
     */
    public void reload(){
         if (isOpen == true){
             catDown();
             if (isDown()){
                closeLatch();
             }
         }    
         System.out.println("reload method called");
    }
    
    /**
     * This method activates the solenoid spikes and launches the arm all the way up
     */
    public void catUp(){
        //upSpike.set(Relay.Value.kOn);
        //downSpike.set(Relay.Value.kOff);        
        leftPiston.set(DoubleSolenoid.Value.kForward); 
        rightPiston.set(DoubleSolenoid.Value.kForward); 
    }
    
    /**
     * This method brings the arm all the way down from the top
     */
    public void catDown(){
        //upSpike.set(Relay.Value.kOn);
        //downSpike.set(Relay.Value.kOff);        
        leftPiston.set(DoubleSolenoid.Value.kReverse); 
        rightPiston.set(DoubleSolenoid.Value.kReverse); 
    }
    
    /**
     * This method opens the latch freeing the catapult
     */
    public void openLatch(){
        //upSpike.set(Relay.Value.kOn);
        //downSpike.set(Relay.Value.kOff);        
        latchPiston.set(DoubleSolenoid.Value.kReverse); 
        isOpen = true;
    }
    
    /**
     * This method closes the latch capturing the catapult
     */
    public void closeLatch(){
        //upSpike.set(Relay.Value.kOn);
        //downSpike.set(Relay.Value.kOff);
        latchPiston.set(DoubleSolenoid.Value.kForward);
        isOpen = false;
    }
    
    /**
     * This method activates the solenoid spikes, but uses the limit switch and only launches the arm midway up
     */
    public void armMiddle(){
        /*while(armMiddleLimit.get() == false) {
            if (downSpike.get() == Relay.Value.kOn && upSpike.get() == Relay.Value.kOff && armMiddleLimit.get() == false){ //Brings the arm up to the middle if the arm is down
                upSpike.set(Relay.Value.kOn);
                downSpike.set(Relay.Value.kOff);
            }else if (upSpike.get() == Relay.Value.kOn && downSpike.get() == Relay.Value.kOff){ //Brings the arm down to the middle if the arm is down
                upSpike.set(Relay.Value.kOff);
                downSpike.set(Relay.Value.kOn);
            }
        }
        upSpike.set(Relay.Value.kOff);
        downSpike.set(Relay.Value.kOff);*/
        if (downSpike.get() == Relay.Value.kOn && upSpike.get() == Relay.Value.kOff && armMiddleLimit.get() == false){ //Brings the arm up to the middle if the arm is down
                upSpike.set(Relay.Value.kOn);
                downSpike.set(Relay.Value.kOff);
        }else{
            upSpike.set(Relay.Value.kOff);
            downSpike.set(Relay.Value.kOn);            
        }
        
    }
    
    /**
     * This method activates the solenoid spikes and brings the arm all the way down
     */
    public void armBottom(){
        downSpike.set(Relay.Value.kOn);
        upSpike.set(Relay.Value.kOff);
    }
    public boolean isBottom() {
        return armDownLimit.get();
    }
    
}

