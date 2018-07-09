package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Ball;
import edu.virginia.engine.display.DisplayObject;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Platform;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab1test.FinalDestination;
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.Fan;
import edu.virginia.engine.display.TreadMill;
import edu.virginia.engine.display.ReverseTreadMill;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class TemplateLevel extends TransporterGame implements MouseListener {
//object setup
	private Platform platform;
        private Trampoline trampoline;
        private TreadMill treadmill;
        private ReverseTreadMill reverseTreadmill;
        private Fan fan;
	private Ball ball;
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private int deaths;
	private int basepoints=20000;
	private int totalpoints;
        
        
	//number of objects they can place
	private int availablePlatforms=3;
        private int availableTrampolines=2;
        private int availableTreadmills=2;
        private int availableReverseTreadmills=2;
        private int availableFans=2;
                
        //icons
        private ArrayList<DisplayObject> icons;
        private DisplayObject platIcon;
        private DisplayObject trampIcon;
        private DisplayObject TreadmillIcon;
        private DisplayObject reverseTreadmillIcon;
        private DisplayObject FanIcon;
        
        //object lists(to check collisions)        
	private ArrayList<Platform> platforms;
        private ArrayList<Trampoline> trampolines;
        private ArrayList<TreadMill> treadmills;
        private ArrayList<ReverseTreadMill> reverseTreadmills;
	private ArrayList<Fan> fans;
	private String playstate = "";
	private DisplayObject currentObject;
	private ArrayList<DisplayObject> userObjects;
	private int spaceClickTime = 0;
	private int wClickTime = 0;
	private int qClickTime = 0;
	private SoundManager sound;
	int old_x;
        int old_y;
	public TemplateLevel() {
		super("Level One: Platforms", 1000,800);
		init();
	}

	public void init() {
            //place finalstate
		this.finalbox = new FinalDestination(this.getMainFrame().getWidth()-130,this.getMainFrame().getHeight()-150);
                
            //place obsticals
            sound = new SoundManager();
		this.platform = new Platform("platform_0");  //172x32px
		platform.setPivotPoint(86, 16);
		platform.setPosition(150, 400);
		platform.setRotation(330);
		this.getMainFrame().addMouseListener(this);

		//ball setup
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		reset(ball);
                
                //add children to class 
                  trampoline=new Trampoline("Tramp_0");
                 // treadmill=new TreadMill();
                 reverseTreadmill = new ReverseTreadMill();
                 fan = new Fan("Fan_0");
        
		//super.addChild(platform);
              //  super.addChild(trampoline);
		//super.addChild(treadmill);
              //  super.addChild(reverseTreadmill);
              //  super.addChild(fan);
		super.addChild(finalbox);
		super.addChild(ball);
		totalpoints = basepoints;
                
                
                
		platforms = new ArrayList<Platform>();
                trampolines = new ArrayList<Trampoline>();
                treadmills= new ArrayList<TreadMill>();
                reverseTreadmills= new ArrayList<ReverseTreadMill>();
                fans = new ArrayList<Fan>();
		platforms.add(platform);
		
		//create Icons
		icons = new ArrayList<DisplayObject>();
		platIcon = new DisplayObject("platform","platform.png", false);
		platIcon.setPosition(50, 25);
		platIcon.setScaleX(0.5);
		platIcon.setScaleY(0.5);
		super.addChild(platIcon);
		icons.add(platIcon);
                
                trampIcon = new DisplayObject("trampoline","trampoline.png", false);
		trampIcon.setPosition(690, 20);
		trampIcon.setScaleX(0.5);
		trampIcon.setScaleY(0.5);
		super.addChild(trampIcon);
		icons.add(trampIcon);
                
                TreadmillIcon = new DisplayObject("treadmill","treadmill_0.png", false);
		TreadmillIcon.setPosition(500, 20);
		TreadmillIcon.setScaleX(0.5);
		TreadmillIcon.setScaleY(0.5);
		super.addChild(TreadmillIcon);
		icons.add(TreadmillIcon);
                
                
                reverseTreadmillIcon = new DisplayObject("treadmill","treadmill_0.png", false);
		reverseTreadmillIcon.setPosition(330, 20);
		reverseTreadmillIcon.setScaleX(0.5);
		reverseTreadmillIcon.setScaleY(0.5);
		super.addChild(reverseTreadmillIcon);
		icons.add(reverseTreadmillIcon);
                
                FanIcon = new DisplayObject("treadmill","fan.png", false);
		FanIcon.setPosition(200, 10);
		FanIcon.setScaleX(0.1);
		FanIcon.setScaleY(0.1);
		super.addChild(FanIcon);
		icons.add(FanIcon);
                
                
                
                
                
                
                
                
                
                
                
		userObjects = new ArrayList<DisplayObject>();
		playstate = "design";
	}
	
	private void reset(Ball b) {
		b.setPosition(100, 200);
		b.setPhysics(false);
		b.setVelX(0);
		b.setVelY(0);
	}
	
	public String getState() {
		return this.playstate;
	}
	
	//Object instantiation
	
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		//totalpoints
		if(!playstate.equals("won")){

			if(totalpoints>0 && this.ball.getPhysics()){
				totalpoints--;
			}
			
			//click timer
			if (spaceClickTime > 0) {
				spaceClickTime--;
			}
			if (qClickTime > 0) {
				qClickTime--;
			}
			if (wClickTime > 0) {
				wClickTime--;
			}

			this.old_x = ball.getPosition()[0];
			this.old_y = ball.getPosition()[1];
			super.update(pressedKeys);
			//System.out.println(Double.toString(ball.getVelX()));
			//System.out.println(Double.toString(ball.getVelY()));
			if (playstate.equals("design") && spaceClickTime == 0) {
				if(pressedKeys.contains(32)){
					this.ball.setPhysics(true);
					playstate="play";
					spaceClickTime = 10;
				}
			} else if (playstate.equals("play") && spaceClickTime == 0) {
				if(pressedKeys.contains(32)){
					reset(ball);
					this.deaths++;
					totalpoints=totalpoints-100;
					this.ball.setPhysics(false);
					playstate = "design";
					spaceClickTime = 10;
				}
			}
			
			if (playstate.equals("design") && userObjects.size()!=0 && currentObject != null) {
				//Transforming platform based on user input
				
				if(pressedKeys.contains(37)){
					//left arrow
					if (currentObject.getPosition()[0] >= 4) {
						currentObject.getPosition()[0] -= 4;
					}
				}
				if(pressedKeys.contains(38)){
					//up arrow
					if (currentObject.getPosition()[1] >= 4) {
						currentObject.getPosition()[1] -= 4;
					}
				}
				if(pressedKeys.contains(39)){
					//right arrow
					if (currentObject.getPosition()[0] <= this.getMainFrame().getWidth()-50) {
						currentObject.getPosition()[0] += 4;
					}
				}
				if(pressedKeys.contains(40)){
					//down arrow
					if (currentObject.getPosition()[1] <= this.getMainFrame().getHeight()-50) {
						currentObject.getPosition()[1] += 4;
					}
				}
				if(pressedKeys.contains(81) && qClickTime == 0){
					//q cycle through objects backwards
					if (userObjects.size()>0) {
						int new_obj_index = userObjects.indexOf(currentObject)-1;
						if (new_obj_index == -1) {
							new_obj_index = userObjects.size()-1;
						}
						currentObject = userObjects.get(new_obj_index);
						qClickTime = 10;
					}
				}
				if(pressedKeys.contains(87) && wClickTime == 0){
					//w cycle through objects forwards
					if (userObjects.size()>0) {
						int new_obj_index = userObjects.indexOf(currentObject)+1;
						if (new_obj_index == userObjects.size()) {
							new_obj_index = 0;
						}
						currentObject = userObjects.get(new_obj_index);
						wClickTime = 10;
					}
				}
				
				if (pressedKeys.contains(65)) {
					currentObject.setRotation(currentObject.getRotation()+5);
				}
				if (pressedKeys.contains(83)) {
					currentObject.setRotation(currentObject.getRotation()-5);
					//System.out.println(Double.toString(platform.getRotation()));
				}
				//System.out.println(Double.toString(platform.getRotation()));
			}
			
			if (playstate.equals("play")) {
				for (Platform plat : platforms) {
					if (ball.collidesWith(plat)) {
						handleCollision(ball, platform);
						ball.setPosition(old_x, old_y);
					}
				}
                                for (Trampoline tramp : trampolines) {
					if (ball.collidesWith(tramp)!=null) {
						handleCollision(ball, tramp, ball.collidesWith(tramp));
						ball.setPosition(old_x, old_y);
					}
				}
                                for (Fan plat : fans) {
					if (ball.collidesWith(plat)!=null) {
                                            if (!ball.collidesWith(plat).equals("fan_bottom")){
                                                 System.out.print("Stuff");
						handleCollision(ball, plat,ball.collidesWith(plat));
						//ball.setPosition(old_x, old_y);
					}if (ball.collidesWith(plat).equals("fan_bottom")) {
        		ball.setPosition(old_x, old_y);
        	}
				}
				if (ball.collidesWith(finalbox) ) {
					handleCollision(ball, finalbox);
					playstate = "won";
					ball.setPhysics(false);
				}
			}
				

//			if (ball.collidesWith(platform)) {
//				handleCollision(ball, platform);
//				ball.setPosition(old_x, old_y);
//			}
			//System.out.println("HHUH> "+ ball.getPosition()[1]+" "+this.getMainFrame().getHeight());
			if(ball.getPosition()[1]>this.getMainFrame().getHeight())	{
				//  System.out.print("HHUH>");
				reset(ball);
				this.deaths++;
				totalpoints=totalpoints-100;
				this.ball.setPhysics(false);
				playstate = "design";
			}
			

			// System.out.println(this.ball.getVelY()+ " "+this.ball.getPhysics());
			/* Make sure platform is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
			//if(platform != null) platform.update(pressedKeys);
			//if(ball != null) ball.update(pressedKeys);
		}
	}}

	private void handleCollision(Ball a, Platform b) {
            sound.PlaySoundEffect("ball");
		System.out.println(Double.toString(ball.getVelX()));
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
		a.setVelX(vels.get(0)*0.8);
		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
			a.setVelX(150);
		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
			a.setVelX(-150);
			//System.out.println("got here");
		}
		a.setVelY(vels.get(1)*0.8);
	}
        private void handleCollision(Ball a, Trampoline b, String hitbox_id) {
             sound.PlaySoundEffect("tramp");
		if (hitbox_id.equals("trampoline_top")) {
			System.out.println("Top");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
			a.setVelX(vels.get(0));
			a.setVelY(vels.get(1));
		} else {
			System.out.println("Bottom");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
			a.setVelX(vels.get(0)*0.2);
			a.setVelY(vels.get(1)*0.2);
		}
	}
	private void handleCollision(Ball a, Fan b, String hitbox_id) {
            if (hitbox_id.equals("fan_top_middle")) {
			//System.out.println("Top"+(this.ball.getPosition()[1])+" "b.getPosition()[1]);
                    //  if(>this.fan.getPosition()[1]){
                    //   System.out.println("stuff "+Math.sin(Math.toRadians(b.getNormal())));
                   
			a.setVelX(a.getVelX()+200*Math.cos(Math.toRadians(b.getNormal())));
			a.setVelY(a.getVelY()-200*Math.sin(Math.toRadians(b.getNormal())));
                       //
                       //
		} else if (hitbox_id.equals("fan_top_right")) {
//			System.out.println("Top"+(this.ball.getPosition()[1])+" "+this.fan.getPosition()[1]);
                    //  if(>this.fan.getPosition()[1]){
  //                     System.out.println("stuff "+Math.sin(Math.toRadians(b.getNormal())));
                   
			a.setVelX(a.getVelX()+50*Math.cos(Math.toRadians(b.getNormal()-45)));
			a.setVelY(a.getVelY()-50*Math.sin(Math.toRadians(b.getNormal())));//left = -cos, right = cos(normal)
                        
		}
               else if (hitbox_id.equals("fan_top_left")) {
//			System.out.println("Top"+(this.ball.getPosition()[1])+" "+this.fan.getPosition()[1]);
                    //  if(>this.fan.getPosition()[1]){
                //       System.out.println("stuff "+Math.sin(Math.toRadians(b.getNormal())));
                //   System.out.print(a.getVelX());
			a.setVelX(a.getVelX()+50*Math.cos(Math.toRadians(b.getNormal()-45)));
			a.setVelY(a.getVelY()-100*Math.sin(Math.toRadians(b.getNormal())));
                        
		} 
                if (hitbox_id.equals("fan_bottom")) {
		//	System.out.println("Bottom");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
			a.setVelX(vels.get(0));
			a.setVelY(vels.get(1));
                        ball.setPosition(old_x,old_y);
                        
		}
	}
	
	//********************
	private void handleCollision(Ball a, FinalDestination b){ //**** handles level completion
		//endgame? listener?  boolean LevelCompleted
                 sound.PlaySoundEffect("win");
		this.LevelCompleted=true;
	}


	@Override
	public void draw(Graphics g){
		if(!LevelCompleted){
			super.draw(g);
                        //g.setFont(font);
			g.drawString("Points = "+totalpoints,this.getMainFrame().getWidth()-100,20);
			g.drawString("Deaths = "+deaths,this.getMainFrame().getWidth()-100,35);
			g.drawString("X "+this.availablePlatforms, 210,35);
			Graphics2D g2d =  (Graphics2D)g;
			if (playstate.equals("design") && currentObject != null) {
				g2d.draw(currentObject.getGlobalHitbox().get(0));
			}
                        g2d.draw(finalbox.getGlobalHitbox().get(0));
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(icons.get(0).getGlobalHitbox().get(0));
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(this.finalbox.getGlobalHitbox().get(0));
			//		g2d.draw(this.platform.getGlobalHitbox());
			//		if(platform != null) platform.draw(g);
			//		if(ball != null) ball.draw(g);
			
		
		}else {g.drawString("LevelComplete", (int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5));
		g.drawString("Points = "+totalpoints,(int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5)+10);
		g.drawString("Deaths = "+deaths, (int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5)+20);
		}
	}
	public static void main(String[] args) {
		TemplateLevel game = new TemplateLevel();
		game.start();
//		game.closeGame();
//		TrampolineTester tramp_game = new TrampolineTester();
//		tramp_game.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (playstate.equals("design")) {
			Area click = new Area(new Rectangle2D.Double(e.getX()+this.getPosition()[0], e.getY()+this.getPosition()[1]-25, 1, 1));
	//		System.out.println(Integer.toString(e.getX()));
	//		System.out.println(Integer.toString(e.getY()));
			for (DisplayObject x : icons) {
				Area icon = new Area(x.getGlobalHitbox().get(0));
				icon.intersect(click);
				if (!icon.isEmpty()) {
					if (availablePlatforms > 0) {
						if (x.getId().equals("platform")) {
							Platform newPlat = new Platform("platform_"+Integer.toString(platforms.size()));
							newPlat.setPosition(500, 400);
							newPlat.setPivotPoint(86, 16);
							System.out.println(newPlat.getId());
							super.addChild(newPlat);
							platforms.add(newPlat);
							userObjects.add(newPlat);
							availablePlatforms--;
							if (currentObject == null) {
								currentObject = newPlat;
							}
						}
					}
                                     if(x.getId().equals("fan")){
                                      if(availableFans>0){
                                        if(x.getId().equals("fan")){
                                            Fan newFan = new Fan("Fan"+Integer.toString(platforms.size()));
							newFan.setPosition(500, 400);
					                newFan.setPivotPoint(250,250);
							System.out.println(newFan.getId());
							super.addChild(newFan);
							fans.add(newFan);
							userObjects.add(newFan);
							availableFans--;
							if (currentObject == null) {
								currentObject = newFan;
							}
                                      
                                      
                                        }
                                      }
				}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
