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
import edu.virginia.engine.display.Fan;
import edu.virginia.engine.display.DisplayObject;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Platform;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab1test.FinalDestination;
import java.awt.Shape;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LevelFour extends TransporterGame implements MouseListener {
        private int old_x;
        private int old_y;
	private Platform platform;
	private Ball ball;
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private int deaths;
	private int basepoints=20000;
	private int totalpoints;
	
	private int availablePlatforms;
        private int availablefans;
	private ArrayList<Platform> platforms;
        private ArrayList<Fan> fans;
	private ArrayList<DisplayObject> icons;
	private DisplayObject platIcon;
	private String playstate = "";
	private DisplayObject currentObject;
	private ArrayList<DisplayObject> userObjects;
	private int spaceClickTime = 0;
	private int wClickTime = 0;
	private int qClickTime = 0;
	private DisplayObject fan;
        private DisplayObject fanIcon;
        private Platform platform2;
        private Platform platform3;
	
	public LevelFour() {
		super("Level Four: Fans", 1000,800);
		init();
	}
	
	public String getState() {
		return this.playstate;
	}

	public void init() {
		this.finalbox = new FinalDestination(this.getMainFrame().getWidth()-180,150);
		this.platform = new Platform("platform_0");  //172x32px
                this.platform2 = new Platform("platform_2");
                this.platform3 = new Platform("platform_3");
                
		platform.setPivotPoint(86, 16);
		platform.setPosition(this.getMainFrame().getWidth()-250, 80);
		platform.setRotation(45);
                
                platform2.setPivotPoint(86, 16);
		platform2.setPosition(this.getMainFrame().getWidth()-400, 240);
		platform2.setRotation(45);
                
                platform3.setPivotPoint(86, 16);
		platform3.setPosition(this.getMainFrame().getWidth()-570, 400);
		platform3.setRotation(45);
		this.getMainFrame().addMouseListener(this);

		//platform.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		reset(ball);
		super.addChild(platform);//**
                super.addChild(platform3);//**
                super.addChild(platform2);//**
		super.addChild(finalbox);//**
		super.addChild(ball);
		totalpoints = basepoints;//**
		
		availablePlatforms = 0;
                availablefans=5;
		platforms = new ArrayList<Platform>();
                platforms.add(platform);
                platforms.add(platform2);
                platforms.add(platform3);
                fans = new ArrayList<Fan>();
		platforms.add(platform);
		
		//create Icons
		icons = new ArrayList<DisplayObject>();
		//platIcon = new DisplayObject("platform","platform.png", false);
               fanIcon = new DisplayObject("fan","fan.png", false);
		
                
                fanIcon.setPosition(50, 20);
		fanIcon.setScaleX(.1);
		fanIcon.setScaleY(0.1);
		super.addChild(fanIcon);
		icons.add(fanIcon);
		userObjects = new ArrayList<DisplayObject>();
		playstate = "design";
	}
	private void reset(Ball b) {
		b.setPosition(100, 200);
		b.setPhysics(false);
		b.setVelX(0);
		b.setVelY(0);
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

			int old_x = ball.getPosition()[0];
			int old_y = ball.getPosition()[1];
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
						plat.handleCollision(ball);
						ball.setPosition(old_x, old_y);
					}
				}
				for (Fan fan : fans) {
					if (ball.collidesWith(fan)!=null) {
						if (!ball.collidesWith(fan).equals("fan_bottom")){
							//System.out.print("Stuff");
							fan.handleCollision(ball,ball.collidesWith(fan));
							//ball.setPosition(old_x, old_y);
						}else if (ball.collidesWith(fan).equals("fan_bottom")) {
							ball.setPosition(old_x, old_y);
						}
					}
				}
				if (ball.collidesWith(finalbox) ) {
					handleCollision(ball, finalbox);
					playstate = "won";
				}
			}

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
		}
	
	//********************
	private void handleCollision(Ball a, FinalDestination b){ //**** handles level completion
		//endgame? listener?  boolean LevelCompleted
		this.LevelCompleted=true;
	}


	@Override
	public void draw(Graphics g){
		if(!LevelCompleted){
			super.draw(g);
			g.drawString("Points = "+totalpoints,this.getMainFrame().getWidth()-100,20);
			g.drawString("Deaths = "+deaths,this.getMainFrame().getWidth()-100,35);
			
                        g.drawString("X "+this.availablefans, 120,50);
			Graphics2D g2d =  (Graphics2D)g;
			
                        
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(icons.get(0).getGlobalHitbox().get(0));
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(this.finalbox.getGlobalHitbox().get(0));
			//		g2d.draw(this.platform.getGlobalHitbox());
			//		if(platform != null) platform.draw(g);
			//		if(ball != null) ball.draw(g);
			if (playstate.equals("design") && currentObject != null) {
				g2d.draw(currentObject.getGlobalHitbox().get(3));
			}
		
		}else {g.drawString("LevelComplete", (int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5));
		g.drawString("Points = "+totalpoints,(int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5)+10);
		g.drawString("Deaths = "+deaths, (int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5)+20);
		}
	}
	public static void main(String[] args) {
		LevelFour game = new LevelFour();
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
                                      if(availablefans>0){
                                        if(x.getId().equals("fan")){
                                            Fan newFan = new Fan("Fan"+Integer.toString(platforms.size()));
							newFan.setPosition(500, 400);
					                newFan.setPivotPoint(250,250);
							System.out.println(newFan.getId());
							super.addChild(newFan);
							fans.add(newFan);
							userObjects.add(newFan);
							availablefans--;
							if (currentObject == null) {
								currentObject = newFan;
							}
                                      
                                      
                                        }
                                      }
				}
			}
		
	}}
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