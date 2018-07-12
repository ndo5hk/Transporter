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
import edu.virginia.engine.display.Fan;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Platform;
import edu.virginia.engine.display.ReverseTreadMill;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.display.TreadMill;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab1test.FinalDestination;
import java.awt.Font;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LevelOne extends Level implements MouseListener {

	//Level specific fields
	private Platform platform;

	
	//Every Level Has These
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private Ball ball;
	private int wClickTime = 0;
	private int qClickTime = 0;
	private int rClickTime = 0;
	private int spaceClickTime = 0;
	Font currentFont ;
	private Sprite exit;
	private String playstate = "";
	private DisplayObject currentObject;
	private ArrayList<DisplayObject> userObjects;
	private ArrayList<SwingPlatform> swing;
	private ArrayList<Platform> platforms;
	private ArrayList<Trampoline> trampolines;
	private ArrayList<TreadMill> treadmills;
	private ArrayList<ReverseTreadMill> reverseTreadmills;
	private ArrayList<Fan> fans;
	HashMap<String, Integer> availableItems;
	private int deaths;
	private int basepoints=20000;
	private int totalpoints;
    private DisplayObject background;
  	private ArrayList<DisplayObject> icons;
  	private DisplayObject platIcon;
  	private DisplayObject trampIcon;
  	private DisplayObject TreadmillIcon;
  	private DisplayObject reverseTreadmillIcon;
  	private DisplayObject FanIcon;
	
        
	public LevelOne(HashMap<String, Integer> map, int width, int height) {
		super("Level One: Platforms",width, height, map);

		init();
	}
	

	public void init() {
//Universal Init stuff
		currentFont= new Font("sansserif",1,15);
		availableItems = super.getAvailableItems();

		//create Icons
		icons = new ArrayList<DisplayObject>();
		platIcon = new DisplayObject("platform","platform.png", false);
		platIcon.setPosition(50, 25);
		platIcon.setScaleX(0.5);
		platIcon.setScaleY(0.5);
		if (availableItems.get("platforms") >0) {
			super.addChild(platIcon);
			icons.add(platIcon);
		}

		trampIcon = new DisplayObject("trampoline","trampoline.png", false);
		trampIcon.setPosition(690, 20);
		trampIcon.setScaleX(0.5);
		trampIcon.setScaleY(0.5);
		if (availableItems.get("trampolines") >0) {
			super.addChild(trampIcon);
			icons.add(trampIcon);
		}

		TreadmillIcon = new DisplayObject("treadmill","treadmill_1.png", false);
		TreadmillIcon.setPosition(500, 20);
		TreadmillIcon.setScaleX(0.5);
		TreadmillIcon.setScaleY(0.5);
		if (availableItems.get("treadmills") >0) {
			super.addChild(TreadmillIcon);
			icons.add(TreadmillIcon);
		}

		reverseTreadmillIcon = new DisplayObject("treadmill_reverse","treadmill_0.png", false);
		reverseTreadmillIcon.setPosition(330, 20);
		reverseTreadmillIcon.setScaleX(0.5);
		reverseTreadmillIcon.setScaleY(0.5);
		if (availableItems.get("reverseTreadmills") >0) {
			super.addChild(reverseTreadmillIcon);
			icons.add(reverseTreadmillIcon);
		}

		FanIcon = new DisplayObject("fan","fan.png", false);
		FanIcon.setPosition(200, 10);
		FanIcon.setScaleX(0.1);
		FanIcon.setScaleY(0.1);
		if (availableItems.get("fans") >0) {
			super.addChild(FanIcon);
			icons.add(FanIcon);
		}


		//list instantiation
		platforms = new ArrayList<Platform>();
		trampolines = new ArrayList<Trampoline>();
		treadmills= new ArrayList<TreadMill>();
		reverseTreadmills= new ArrayList<ReverseTreadMill>();
		fans = new ArrayList<Fan>();
		userObjects = new ArrayList<DisplayObject>();

		//other
		playstate = "design";
		
//Level specific init stuff
		this.background = new DisplayObject("background1","back3.png",false);
		background.setScaleX(2.5);
		background.setScaleY(2.5);
		background.setAlpha(.5f);
		this.addChild(background);
		this.finalbox = new FinalDestination(super.getWidth()-200,super.getHeight()-200);
		this.finalbox.setScaleX(.2);
		this.finalbox.setScaleY(.2);
		this.platform = new Platform("platform_0");  //172x32px
		platform.setPivotPoint(86, 16);
		platform.setPosition(150, 400);
		platform.setRotation(330);
		this.exit = new Sprite("exit","cancel.png",false);
		exit.setPosition(960,10);
		exit.setScaleX(.1);
		exit.setScaleY(.1);
		this.addChild(exit);
		icons.add(exit);

		//platform.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		reset(ball);
		this.addChild(platform);
		this.addChild(finalbox);//**
		this.addChild(ball);
		totalpoints = basepoints;//**

		platforms.add(platform);
		

	}
	
	//Level Specific methods

	private void reset(Ball b) {
		b.setPosition(100, 200);
		b.setPhysics(false);
		b.setVelX(0);
		b.setVelY(0);
	}

	//Universal methods

	public String getState() {
		return this.playstate;
	}

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
			if (rClickTime > 0) {
				rClickTime--;
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
				if (pressedKeys.contains(82) && rClickTime == 0){
					if (currentObject != null) {
						if (this.currentObject.getId().contains("platform")) {
							this.platforms.remove(currentObject);
							super.removeObject("platform", currentObject);
							this.removeChild(currentObject);
						} else if (this.currentObject.getId().contains("reverse")) {
							this.reverseTreadmills.remove(currentObject);
							super.removeObject("reverseTreadmill", currentObject);
							this.removeChild(currentObject);
						} else if (this.currentObject.getId().contains("treadmill")) {
							this.treadmills.remove(currentObject);
							super.removeObject("treadmill", currentObject);
							this.removeChild(currentObject);
						} else if (this.currentObject.getId().contains("fan")) {
							this.fans.remove(currentObject);
							super.removeObject("fan", currentObject);
							this.removeChild(currentObject);
						} else if (this.currentObject.getId().contains("trampoline")) {
							this.trampolines.remove(currentObject);
							super.removeObject("trampoline", currentObject);
							this.removeChild(currentObject);
						}
						userObjects.remove(currentObject);
						if (userObjects.size() > 0) {
							currentObject = userObjects.get(0);
						} else {
							currentObject = null;
						}
						
					}
					rClickTime = 10;
				} 
				if (pressedKeys.contains(81) && qClickTime == 0) {
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

			g.drawString("X "+availableItems.get("platforms"), 210,35);
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
						super.setExit(true);
						this.setExit(true);
					}
					if (availableItems.get("platforms") > 0) {
						if (x.getId().equals("platform")) {
							Platform newPlat = new Platform("platform_"+Integer.toString(platforms.size()));
							newPlat.setPosition(500, 400);
							newPlat.setPivotPoint(86, 16);
							//System.out.println(newPlat.getId());
							this.addChild(newPlat);
							platforms.add(newPlat);
							userObjects.add(newPlat);
							availableItems.put("platforms", availableItems.get("platforms")-1);

							currentObject = newPlat;
						}
					}
					if(availableItems.get("fans")>0){
						if(x.getId().equals("fan")){
							if(x.getId().equals("fan")){
								Fan newFan = new Fan("Fan"+Integer.toString(platforms.size()));
								newFan.setPosition(500, 400);
								newFan.setPivotPoint(250,250);
								//System.out.println(newFan.getId());
								this.addChild(newFan);
								fans.add(newFan);
								userObjects.add(newFan);
								availableItems.put("fans", availableItems.get("fans")-1);
								currentObject = newFan;
							}	
						}
					}
					if(x.getId().equals("trampoline")){
						if(availableItems.get("trampolines")>0){
							if(x.getId().equals("trampoline")){
								Trampoline newTramp = new Trampoline("Trampoline"+Integer.toString(platforms.size()));
								newTramp.setPosition(500, 400);
								newTramp.setPivotPoint(86, 16);
								//System.out.println(newTramp.getId());
								this.addChild(newTramp);
								trampolines.add(newTramp);
								userObjects.add(newTramp);
								availableItems.put("trampolines", availableItems.get("trampolines")-1);

								currentObject = newTramp;
							}
						}
					}
					if (x.getId().equals("treadmill")) {
						if (availableItems.get("treadmills") > 0) {
							TreadMill newTread = new TreadMill();
							newTread.setPosition(500, 400);
							newTread.setPivotPoint(86, 16);
							newTread.addActionAnimation("run", 0, 23, 1);
							//System.out.println(newTread.getId());
							this.addChild(newTread);
							treadmills.add(newTread);
							userObjects.add(newTread);
							availableItems.put("treadmills", availableItems.get("treadmills")-1);

							currentObject = newTread;

						}
					} else if (x.getId().equals("treadmill_reverse")) {
						if (availableItems.get("reverseTreadmills") > 0) {
							ReverseTreadMill newRevTread = new ReverseTreadMill();
							newRevTread.setPosition(500, 400);
							newRevTread.setPivotPoint(86, 16);
							newRevTread.addActionAnimation("run", 0, 23, 1);
							//							System.out.println(newRevTread.getId());
							this.addChild(newRevTread);
							reverseTreadmills.add(newRevTread);
							userObjects.add(newRevTread);
							availableItems.put("reverseTreadmills", availableItems.get("reverseTreadmills")-1);

							currentObject = newRevTread;

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
