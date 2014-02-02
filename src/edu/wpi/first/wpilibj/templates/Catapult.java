/*
 * This class controls the shooting arm on the roobut
 */


/*     ##   ##       YYYY    YYYY      OOOOO      LLLLL            OOOOO              SSSSSSSS    WWWWW            WWWWW     AAAAAAA           GGGGGGGG  
       ##   ##        YY      YY     OO     OO     LLL           OO     OO           SSS     SS    WW                WW       AAAAA         GGGG      GGG
   #############       YY    YY     OOO     OOO    LLL          OOO     OOO          SSSS           WW              WW       AA   AA       GGGGG        
      ##   ##            YYYY       OOO     OOO    LLL          OOO     OOO             SSSSS        WW    WWWW    WW       AAAAAAAAA      GGGGG
   #############          YY        OOO     OOO    LLL          OOO     OOO                SSSS       WW WW    WW WW       AA       AA     GGGGG     GGGGGG
     ##   ##              YY         OO     OO     LLL     LL    OO     OO           SS     SSS        WWW      WWW       AA         AA     GGGG      GG GG
     ##   ##            YYYYYY         OOOOO      LLLLLLLLLL       OOOOO              SSSSSSSS       WWWWWWW  WWWWWWW   AAAAA       AAAAA     GGGGGGGGG  GG



       ##   ##          444  444     2222222         00000               BBBBBBBB     LLLLL              AAAAAAA        ZZZZZZZZZZ   EEEEEEEEEEE             IIIIII  TTTTTTTTTTTT
       ##   ##         444   444    22     222     00     00              BB   BBB     LLL                AAAAA         ZZ     ZZ      EE     EE               II    TT   TT   TT
   #############      444    444           222    000     000             BB   BBB     LLL               AA   AA             ZZ        EE  E                   II         TT
      ##   ##        444444444444        222      000     000             BBBBBBBB     LLL              AAAAAAAAA           ZZ         EEEEE                   II         TT
   #############             444        222       000     000             BB    BBB    LLL             AA       AA         ZZ          EE  E                   II         TT
     ##   ##                 444      2222         00     00              BB    BBB    LLL     LL     AA         AA      ZZ     ZZ     EE     EE               II         TT
     ##   ##                 444    22222222222      00000               BBBBBBBBB    LLLLLLLLLL    AAAAA       AAAAA   ZZZZZZZZZZ   EEEEEEEEEEE             IIIIII     TTTTTT



*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

public class Catapult {
        
    //Solenoid upSol;
    //Solenoid downSpike;
    DigitalInput armDownLimit; 
    DigitalInput armLoadedLimit;
    DigitalInput armMiddleLimit;
    Relay upSpike;
    Relay downSpike;
        
    /**
     * This is a constructor method to initialize ports given into the method through RobotTemplate.java
     * @param SPI1 port for the up spike of the catapult solenoid
     * @param SPI2 port for the down spike of the catapult solenoid
     * @param LSPDOWN port for the isDown limit switch
     * @param LSPLOADED port for the isLoaded limit switch
     * @param LSPMIDDLE port for the armBottomMiddle limit switch on the pistons
     */
    public void Catapult(final int SPI1, final int SPI2, final int LSPDOWN, final int LSPLOADED, final int LSPMIDDLE){
        
        //upSol = new Solenoid(sol1);
        //downSpike = new Solenoid(sol2);
        upSpike = new Relay(SPI1); //If upSol is set to Relay.Value.kOn and downSpike is set to Relay.Value.kOff then the arm will go up
        downSpike = new Relay(SPI2); //If downSpike is set to Relay.Value.kOn and upSol is set to Relay.Value.kOff then the arm will go down
        armDownLimit = new DigitalInput(LSPDOWN);     
        armLoadedLimit = new DigitalInput(LSPLOADED);
        armMiddleLimit = new DigitalInput(LSPMIDDLE);
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
        }else{
            loadValue = false;
        }
        return loadValue;
    }
    
    /**
     * This method activates the solenoid spikes and launches the arm all the way up
     */
    public void armTop(){
        upSpike.set(Relay.Value.kOn);
        downSpike.set(Relay.Value.kOff);        
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
    
    public void driveForward(){
    
    }
}