/*
 * This class controls the shooting arm on the roboto
 */


/*     ##   ##       YYYY    YYYY      OOOOO      LLLLL            OOOOO              SSSSSSSS    WWWWW            WWWWW     AAAAAAA           GGGGGGGG  
       ##   ##        YY      YY     OO     OO     LLL           OO     OO           SSS     SS    WW                WW       AAAAA         GGGG      GGG
   #############       YY    YY     OOO     OOO    LLL          OOO     OOO          SSSS           WW              WW       AA   AA       GGGGG        
      ##   ##            YYYY       OOO     OOO    LLL          OOO     OOO             SSSSS        WW    WWWW    WW       AAAAAAAAA      GGGGG
   #############          YY        OOO     OOO    LLL          OOO     OOO                SSSS       WW WW    WW WW       AA       AA     GGGGG     GGGGGG
     ##   ##              YY         OO     OO     LLL     LL    OO     OO           SS     SSS        WWW      WWW       AA         AA     GGGG      GG GG
     ##   ##            YYYYYY         OOOOO      LLLLLLLLLL       OOOOO              SSSSSSSS       WWWWWWW  WWWWWWW   AAAAA       AAAAA     GGGGGGGGG  GG



       ##   ##          444  444     2222222         00000               BBBBBBBB     LLLLL              AAAAAAA       ZZZZZZZZZZZ
       ##   ##         444   444    22     222     00     00              BB   BBB     LLL                AAAAA         ZZ      ZZ
   #############      444    444           222    000     000             BB   BBB     LLL               AA   AA         ZZ
      ##   ##        444444444444        222      000     000             BBBBBBBB     LLL              AAAAAAAAA          ZZ
   #############             444        222       000     000             BB    BBB    LLL             AA       AA          ZZ
     ##   ##                 444      2222         00     00              BB    BBB    LLL     LL     AA         AA     ZZ    ZZ 
     ##   ##                 444    22222222222      00000               BBBBBBBBB    LLLLLLLLLL    AAAAA       AAAAA   ZZZZZZZZZZ


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
