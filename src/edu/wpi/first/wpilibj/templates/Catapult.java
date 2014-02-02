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
    //Solenoid downSol;
    DigitalInput armDownLimit; 
    DigitalInput armLoadedLimit;
    DigitalInput armMiddleLimit;
    Relay upSol;
    Relay downSol;
        
    /**
     * This is a constructor method to initialize ports given into the method through RobotTemplate.java
     * @param sol1 port for the up spike of the catapult solenoid
     * @param sol2 port for the down spike of the catapult solenoid
     * @param limitSwitchPortDown port for the isDown limit switch
     * @param limitSwitchPortLoaded port for the isLoaded limit switch
     * @param limitSwitchPortMiddle port for the armBottomMiddle limit switch on the pistons
     */
    public void Catapult(int sol1, int sol2, int limitSwitchPortDown, int limitSwitchPortLoaded, int limitSwitchPortMiddle){
        
        //upSol = new Solenoid(sol1);
        //downSol = new Solenoid(sol2);
        upSol = new Relay(sol1); //If upSol is set to Relay.Value.kOn and downSol is set to Relay.Value.kOff then the arm will go up
        downSol = new Relay(sol2); //If downSol is set to Relay.Value.kOn and upSol is set to Relay.Value.kOff then the arm will go down
        armDownLimit = new DigitalInput(limitSwitchPortDown);     
        armLoadedLimit = new DigitalInput(limitSwitchPortLoaded);
        armMiddleLimit = new DigitalInput(limitSwitchPortMiddle);
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
        upSol.set(Relay.Value.kOn);
        downSol.set(Relay.Value.kOff);        
    }
    
    /**
     * This method activates the solenoid spikes, but uses the limit switch and only launches the arm midway up
     */
    public void armMiddle(){
        while(armMiddleLimit.get() == false) {
            if (downSol.get() == Relay.Value.kOn && upSol.get() == Relay.Value.kOff){
                upSol.set(Relay.Value.kOn);
                downSol.set(Relay.Value.kOff);
            }else if (upSol.get() == Relay.Value.kOn && downSol.get() == Relay.Value.kOff){
                upSol.set(Relay.Value.kOff);
                downSol.set(Relay.Value.kOn);
            }
        }
        upSol.set(Relay.Value.kOff);
        downSol.set(Relay.Value.kOff);
    }
    
    /**
     * This method activates the solenoid spikes and brings the arm all the way down
     */
    public void armBottom(){
        downSol.set(Relay.Value.kOn);
        upSol.set(Relay.Value.kOff);
    }
    
    public void driveForward(){
    
    }
}

