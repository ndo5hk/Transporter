package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

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
import java.awt.Font;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LevelOne extends Level implements MouseListener {

	private Platform platform;
	private Ball ball;
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private int deaths;
	private int basepoints=20000;
	private int totalpoints;
    private DisplayObject background;
        boolean exitbool;
	
	private int availablePlatforms;
	private ArrayList<Platform> platforms;
	private ArrayList<DisplayObject> icons;
	private DisplayObject platIcon;
	private String playstate = "";
	private DisplayObject currentObject;
	private ArrayList<DisplayObject> userObjects;
	private int spaceClickTime = 0;
	private int wClickTime = 0;
	private int qClickTime = 0;
	Font currentFont ;
	private Sprite exit;
        
	public LevelOne(HashMap<String, Integer> map, int width, int height) {
		super("Level One: Platforms",width, height, map);

		init();
	}

	public void init() {
    currentFont= new Font("sansserif",1,15);
		this.background = new DisplayObject("background1","back3.png",false);
		background.setScaleX(2.5);
		background.setScaleY(2.5);
		this.addChild(background);
		this.finalbox = new FinalDestination(super.getWidth()-130,super.getHeight()-150);
		this.platform = new Platform("platform_0");  //172x32px
		platform.setPivotPoint(86, 16);
		platform.setPosition(150, 400);
		platform.setRotation(330);

		//platform.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		reset(ball);
		super.addChild(platform);
		super.addChild(finalbox);//**
		super.addChild(ball);
		totalpoints = basepoints;//**
		
		availablePlatforms = 2;
		platforms = new ArrayList<Platform>();
		platforms.add(platform);
		
		//create Icons
		icons = new ArrayList<DisplayObject>();
		platIcon = new DisplayObject("platform","platform.png", false);
		platIcon.setPosition(100, 20);
		platIcon.setScaleX(0.5);
		platIcon.setScaleY(0.5);
		super.addChild(platIcon);
		icons.add(platIcon);
		userObjects = new ArrayList<DisplayObject>();
		playstate = "design";
                this.exit = new Sprite("exit","cancel.png",false);
		exit.setPosition(960,10);
		exit.setScaleX(.1);
		exit.setScaleY(.1);
		
                this.addChild(exit);
                icons.add(exit);
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
//	public void setState(String state) {
//		this.playstate = state;
//	}
	
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
					if (currentObject.getPosition()[0] <= super.getWidth()-50) {
						currentObject.getPosition()[0] += 4;
					}
				}
				if(pressedKeys.contains(40)){
					//down arrow
					if (currentObject.getPosition()[1] <= super.getHeight()-50) {
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
			if(ball.getPosition()[1]>super.getHeight())	{
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

//	private void handleCollision(Ball a, Platform b) {
//		System.out.println(Double.toString(ball.getVelX()));
//		ArrayList<Double> vels = super.getElasticCollisionVels(a, b);
//		a.setVelX(vels.get(0));
////		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
////			a.setVelX(150);
////		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
////			a.setVelX(-150);
//			//System.out.println("got here");
////		}
//		a.setVelY(vels.get(1)*0.4);
//	}
	
	
	//********************
	private void handleCollision(Ball a, FinalDestination b){ //**** handles level completion
		//endgame? listener?  boolean LevelCompleted
		this.LevelCompleted=true;
		super.setCompleted(true);
	}
        
        public void setExit(boolean what){
        this.exitbool = what;
        }
        public boolean getExit(){
        return exitbool;
        }


	@Override
	public void draw(Graphics g){
		if(!LevelCompleted){
			super.draw(g);
      g.setFont(currentFont);
			g.drawString("Points = "+totalpoints,super.getWidth()-150,20);
			g.drawString("Deaths = "+deaths,super.getWidth()-150,35);

			g.drawString("X "+this.availablePlatforms, 210,35);
			Graphics2D g2d =  (Graphics2D)g;
			if (playstate.equals("design") && currentObject != null) {
				g2d.draw(currentObject.getGlobalHitbox().get(0));
			}
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(icons.get(0).getGlobalHitbox().get(0));
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(this.finalbox.getGlobalHitbox().get(0));
			//		g2d.draw(this.platform.getGlobalHitbox());
			//		if(platform != null) platform.draw(g);
			//		if(ball != null) ball.draw(g);
			
		
		}else {g.drawString("LevelComplete", (int)(super.getWidth()*.5), (int)(super.getHeight()*.5));
		g.drawString("Points = "+totalpoints,(int)(super.getWidth()*.5), (int)(super.getHeight()*.5)+10);
		g.drawString("Deaths = "+deaths, (int)(super.getWidth()*.5), (int)(super.getHeight()*.5)+20);
		}
	}
//	public static void main(String[] args) {
//		LevelOne game = new LevelOne();
//		game.start();
////		game.closeGame();
////		TrampolineTester tramp_game = new TrampolineTester();
////		tramp_game.start();
//	}

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
                                    if (x.getId().equals("exit")) {
                                        System.out.print("EXIT");
                                        super.setExit(true);
                                            this.setExit(true);
					}
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
							
								currentObject = newPlat;
							
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
