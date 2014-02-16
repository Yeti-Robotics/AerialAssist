/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 */
public class Box {
    
    Relay reelSpike;
    
    /**
     * Constructor class for the box
     * @param REEL_RELAY_POS Port for the motor that controls the reel for the box
     */
    public Box (int REEL_RELAY_POS){
        reelSpike = new Relay(REEL_RELAY_POS);
    }
    
    /**
     * Opens the box
     */
    public void openBox(){
        reelSpike.set(Relay.Value.kForward);
    }
    
    public void closeBox(){
        reelSpike.set(Relay.Value.kReverse);
    }
}
