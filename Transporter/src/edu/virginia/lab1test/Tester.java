package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class Tester extends Game implements MouseListener{

	/* Create a sprite object for our game. We'll use mario */
	//
    
   // static AnimatedSprite mario = new AnimatedSprite("mario_", 6, false);
	
        Sprite trampoline;
        Sprite ramp;
        Sprite speed;
        Sprite slow;
        Sprite platform;
	int angle;
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public Tester() {
		super("Tester", 800, 600);
		super.getMainFrame().addMouseListener(this);
                TranpolineTest trampolin= new TranpolineTest();
                SpeedupTest tread= new SpeedupTest();
                RampTest rampthing = new RampTest();
                SlowdownTest sandshit = new SlowdownTest();
                PlatformTester plat = new PlatformTester();
                
                
                
                this.platform = plat.getSprite();
                this.platform.setPivotPoint(this.platform.getUnscaledHeight(), angle);
                this.speed=tread.getSprite();
                this.trampoline=trampolin.getSprite();
                this.ramp =rampthing.getSprite();
                this.slow= sandshit.getSprite();
                
		//this.parent.addChild(platform);
               // this.parent.addChild(trampoline);
               // this.trampoline.setParent(parent);
               // this.platform.setParent(parent);
                this.platform.setScaleY(.5);
                this.platform.setScaleX(.8);
                this.platform.setPosition(100, 100);
                this.speed.setPosition(300, 100);
                this.trampoline.setPosition(50, 100);
                this.ramp.setPosition(580, 100);
                 this.slow.setPosition(650, 100);
                this.angle = 0;
                
                this.trampoline.setPosition(480, 100);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
            
		if(pressedKeys.contains(37)){
            //left arrow
			if (platform.getPosition()[0] >= 4) {
				platform.getPosition()[0] -= 4;
			}
		}
        if(pressedKeys.contains(38)){
        //up arrow
			if (platform.getPosition()[1] >= 4) {
				platform.getPosition()[1] -= 4;
			}
        }
        if(pressedKeys.contains(39)){
        //right arrow
			if (platform.getPosition()[0] <= 680) {
				platform.getPosition()[0] += 4;
			}
        }
        if(pressedKeys.contains(40)){
        //down arrow
			if (platform.getPosition()[1] <= 460) {
				platform.getPosition()[1] += 4;
			}
        }
       //ROTATIONS
          if(pressedKeys.contains(81)){
          //q
          this.platform.setRotation(this.platform.getRotation()+10);
          this.angle+=10;
          }
                    if(pressedKeys.contains(87)){
                    //w
                     this.platform.setRotation(this.platform.getRotation()-10);
                     this.angle-=10;
                    }
      if(pressedKeys.contains(38) || pressedKeys.contains(40)){
      
      } else if (pressedKeys.contains(39) || pressedKeys.contains(37)) {
    	 
      } else {
    
      }
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(platform != null) platform.update(pressedKeys);
                System.out.println(this.platform.getRotation());
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
//		if(mario != null) mario.draw(g);

		
                Graphics2D g2d = (Graphics2D)g;
		g2d.draw(this.platform.getGlobalHitbox());
              
		if(platform != null) {
                    slow.draw(g);
                    speed.draw(g);
                     ramp.draw(g);
                    trampoline.draw(g);
                    platform.draw(g);
                   
                //if i draw ramp before it doesnt get impacted by platform
                }
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		Tester game = new Tester();
		
		game.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
