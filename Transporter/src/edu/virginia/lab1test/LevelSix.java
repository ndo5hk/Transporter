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
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.Fan;
import edu.virginia.engine.display.TreadMill;
import edu.virginia.engine.display.ReverseTreadMill;
import java.awt.Font;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LevelSix extends Level implements MouseListener {
	//object setup
	boolean exitbool;
	private Platform platform0;
	private Platform platform1;
	private Platform platform2;
	private Platform platform3;
	private Platform platform4;
	private Platform platform5;
	private Platform platform6;
	private Platform platform7;
	private Platform platform8;
	private Platform platform9;
	private Platform platform10;

	private ArrayList<SwingPlatform> swingarray;
	private SwingPlatform swingplatform0;
	private SwingPlatform swingplatform1;
	private SwingPlatform swingplatform2;
	private SwingPlatform swingplatform3;
	private SwingPlatform swingplatform4;
	private SwingPlatform swingplatform5;

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
	private DisplayObject background;
	Font currentFont = new Font("sansserif",1,15);

	//number of objects they can place
	private int availablePlatforms;
	private int availableTrampolines;
	private int availableTreadmills;
	private int availableReverseTreadmills;
	private int availableFans;

	//icons
	private ArrayList<DisplayObject> icons;
	private DisplayObject platIcon;
	private DisplayObject trampIcon;
	private DisplayObject TreadmillIcon;
	private DisplayObject reverseTreadmillIcon;
	private DisplayObject FanIcon;

	//object lists(to check collisions)      
	private ArrayList<SwingPlatform> swing;
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
	private Sprite exit;
	int old_x;
	int old_y;
	HashMap<String, Integer> availableItems;

	public LevelSix(HashMap<String, Integer> map, int width, int height) {
		super("Level Six: Broken Hearts", width,height, map);
		init();

	}

	public void init() {
		availableItems = super.getAvailableItems();
		this.background = new DisplayObject("background1","back3.png",false);
		super.addChild(this.background);
		this.exit = new Sprite("exit","cancel.png",false);
		exit.setPosition(960,10);
		exit.setScaleX(.1);
		exit.setScaleY(.1);

		background.setScaleX(2.5);
		background.setScaleY(2.5);
		background.setAlpha(.5f);


		//place finalstate
		this.finalbox = new FinalDestination(super.getWidth()-200,super.getHeight()-200);

		//place obsticals
		sound = new SoundManager();

		platform0 = new Platform("platform_0");  //172x32px
		this.platform1 = new Platform("platform_1");
		this.platform2 = new Platform("platform_2");
		this.platform3 = new Platform("platform_3");
		this.platform4 = new Platform("platform_4");
		this.platform5 = new Platform("platform_5");
		this.platform6 = new Platform("platform_6");
		this.platform7 = new Platform("platform_7");
		this.platform8 = new Platform("platform_8");
		this.platform9 = new Platform("platform_9");
		this.platform10 = new Platform("platform_10");


		swing = new ArrayList<SwingPlatform>();
		this.swingplatform0=new SwingPlatform();
		this.swingplatform1=new SwingPlatform();
		this.swingplatform2=new SwingPlatform();
		this.swingplatform3=new SwingPlatform();
		this.swingplatform4=new SwingPlatform();
		this.swingplatform5=new SwingPlatform();
		swing.add(swingplatform0);
		//swing.add(swingplatform1);
		// swing.add(swingplatform2);
		// swing.add(swingplatform3);
		// swing.add(swingplatform4);
		// swing.add(swingplatform5);


		swingplatform0.setPosition(500,400);
		swingplatform0.setRotation(270);

		//  swingplatform1.setPosition(650,550);
		//   swingplatform1.setRotation(270);

		/*   swingplatform2.setPosition(1500,400);
                swingplatform2.setRotation(270);

                 swingplatform3.setPosition(1400,400);
                swingplatform3.setRotation(270);

                 swingplatform4.setPosition(1500,400);
                swingplatform4.setRotation(270);

                 swingplatform5.setPosition(1500,400);
                swingplatform5.setRotation(270);
		 */




		platform0.setPivotPoint(86, 16);
		platform0.setPosition(200, 300);
		platform0.setRotation(0);
		platform1.setPivotPoint(86, 16);
		platform1.setPosition(320, 300);
		platform1.setRotation(0);
		platform2.setPivotPoint(86, 16);
		platform2.setPosition(460, 300);
		platform2.setRotation(0);
		platform3.setPivotPoint(86, 16);
		platform3.setPosition(630, 300);
		platform3.setRotation(0);
		platform4.setPivotPoint(86, 16);
		platform4.setPosition(800, 300);
		platform4.setRotation(0);


		platform5.setPosition(0, 500);
		platform5.setRotation(0);
		platform6.setPivotPoint(0, 0);
		platform6.setPosition(170, 500);
		platform6.setRotation(0);
		platform7.setPivotPoint(0, 0);
		platform7.setPosition(660, 500);
		platform7.setRotation(0);
		platform8.setPivotPoint(0, 0);
		platform8.setPosition(800, 500);
		platform8.setRotation(0);
		platform9.setPivotPoint(0, 0);
		platform9.setPosition(900, 500);
		platform9.setRotation(0);

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
		platforms.add(platform0);
		platforms.add(platform1);
		platforms.add(platform2);
		platforms.add(platform3);
		platforms.add(platform4);
		platforms.add(platform5);
		platforms.add(platform6);
		platforms.add(platform7);
		platforms.add(platform8);
		platforms.add(platform9);
		//   platforms.add(platform10);




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

		TreadmillIcon = new DisplayObject("treadmill","treadmill_1.png", false);
		TreadmillIcon.setPosition(500, 20);
		TreadmillIcon.setScaleX(0.5);
		TreadmillIcon.setScaleY(0.5);
		super.addChild(TreadmillIcon);
		icons.add(TreadmillIcon);


		reverseTreadmillIcon = new DisplayObject("treadmill_reverse","treadmill_0.png", false);
		reverseTreadmillIcon.setPosition(330, 20);
		reverseTreadmillIcon.setScaleX(0.5);
		reverseTreadmillIcon.setScaleY(0.5);
		super.addChild(reverseTreadmillIcon);
		icons.add(reverseTreadmillIcon);

		FanIcon = new DisplayObject("fan","fan.png", false);
		FanIcon.setPosition(200, 10);
		FanIcon.setScaleX(0.1);
		FanIcon.setScaleY(0.1);
		super.addChild(FanIcon);
		icons.add(FanIcon);


		this.addChild(platform0);
		this.addChild(platform1);
		this.addChild(platform2);
		this.addChild(platform3);
		this.addChild(platform4);
		this.addChild(platform5);
		this.addChild(platform6);
		this.addChild(platform7);
		this.addChild(platform8);
		this.addChild(platform9);
		//    this.addChild(platform10);

		this.addChild(swingplatform0);
		//   this.addChild(swingplatform1);
		//   this.addChild(swingplatform2);
		//  this.addChild(swingplatform3);
		//  this.addChild(swingplatform4);
		//  this.addChild(swingplatform5);




		availablePlatforms=2;
		availableTrampolines=2;
		availableTreadmills=2;
		availableReverseTreadmills=2;
		availableFans=2;

		this.addChild(exit);
		icons.add(exit);
		ball.setPosition(500,150);

		userObjects = new ArrayList<DisplayObject>();
		playstate = "design";
	}

	private void reset(Ball b) {
		b.setPosition(500, 150);
		b.setPhysics(false);
		b.setVelX(0);
		b.setVelY(0);
	}

	public String getState() {
		return this.playstate;
	}

	//Object instantiation
	public void setExit(boolean what){
		this.exitbool = what;
	}
	public boolean getExit(){
		return exitbool;
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys){

		for (TreadMill tm : treadmills) {
			tm.animate("run");
		}
		for (ReverseTreadMill tm : reverseTreadmills) {
			tm.animate("run");
		}
		//totalpoints
		for(SwingPlatform x:swing){
			x.swing();
		}
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
						sound.PlaySoundEffect("ball");
						sound.updateClock();
						System.out.println("hoos "+plat.getId());
						plat.handleCollision(ball);

						ball.setPosition(old_x, old_y);
					}
				}

				for (Trampoline tramp : trampolines) {
                                    //System.out.println("TRAMP STUFF:");
					if (ball.collidesWith(tramp)!=(null)) {
                                           
                                             System.out.println("TRAMP: "+ball.collidesWith(tramp));
                                                  if(ball.collidesWith(tramp).equals("trampoline_top")){
                                                     // System.out.print("Fucked");
                                                   sound.PlaySoundEffect("tramp");
                                                   sound.updateClock();
                                                   //sound.updateClock();
                                                  }
                                                   if(ball.collidesWith(tramp).equals("trampoline_bottom")){
                                                   sound.PlaySoundEffect("ball");
                                                   sound.updateClock();
                                                  }
                                                    
                                                    
                                             
						tramp.handleCollision(ball,ball.collidesWith(tramp));
						//if(ball.collidesWith(plat).equals("top"))
						ball.setPosition(old_x, old_y);
					}

				}
                               
				for (Fan fan : fans) {
					if (ball.collidesWith(fan)!=null) {
						System.out.println("hoos "+fan.getId());
						if (!ball.collidesWith(fan).equals("fan_bottom")){
							//System.out.print("Stuff");
							fan.handleCollision(ball,ball.collidesWith(fan));
							sound.PlaySoundEffect("fan");
              sound.updateClock();

							//ball.setPosition(old_x, old_y);
						}if (ball.collidesWith(fan).equals("fan_bottom")) {
                                                   
                                                        sound.PlaySoundEffect("ball");
                                                         sound.updateClock();
                                                       
							ball.setPosition(old_x, old_y);
						}
					}

				}
				for (TreadMill tm : treadmills) {
					if (ball.collidesWith(tm)) {
						System.out.println("hoos "+tm.getId());
						tm.handleCollision(ball);
						ball.setPosition(old_x, old_y);
					}
				}
				for (ReverseTreadMill tm : this.reverseTreadmills) {

					if (ball.collidesWith(tm)) {
						System.out.println("hoos "+tm.getId());
						tm.handleCollision(ball);
						ball.setPosition(old_x, old_y);
					}
				}
				for (SwingPlatform swinging : swing) {
					if (ball.collidesWith(swinging)) {
						System.out.println("hoos "+swinging.getId());
						swinging.handleCollision(ball);
						ball.setPosition(old_x+10, old_y);
                                                sound.PlaySoundEffect("ball");
                                                         sound.updateClock();
					}
				}
				if (ball.collidesWith(finalbox) ) {
					handleCollision(ball, finalbox);
					playstate = "won";
					ball.setPhysics(false);
				}	

				//			if (ball.collidesWith(platform)) {
				//				Collision(ball, platform);
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
	}

	//********************
	private void handleCollision(Ball a, FinalDestination b){ //**** handles level completion
		//endgame? listener?  boolean LevelCompleted
		sound.PlaySoundEffect("win");
		this.LevelCompleted=true;
		// this.sound.

	}
       

	@Override
	public void draw(Graphics g){
		if(!LevelCompleted){
			super.draw(g);
			//g.setFont(font);
			g.setFont(currentFont);
			g.drawString("Points = "+totalpoints,super.getWidth()-150,20);
			g.drawString("Deaths = "+deaths,super.getWidth()-150,35);
			g.drawString("X "+this.availablePlatforms, 160,35);
			g.drawString("X "+this.availableTreadmills, 620,35);
			g.drawString("X "+this.availableReverseTreadmills, 455,35);
			g.drawString("X "+this.availableTrampolines, 800,35);
			g.drawString("X "+this.availableFans, 270,35);

			Graphics2D g2d =  (Graphics2D)g;
			if (playstate.equals("design") && currentObject != null) {
				g2d.draw(currentObject.getGlobalHitbox().get(0));
			}
			//g2d.draw(exit.getGlobalHitbox().get(0));
			//			Graphics2D g2d =  (Graphics2D)g;
			//			g2d.draw(icons.get(0).getGlobalHitbox().get(0));
			//Graphics2D g2d =  (Graphics2D)g;
			//	g2d.draw(this.finalbox.getGlobalHitbox().get(0));
			//		g2d.draw(this.platform.getGlobalHitbox());
			//		if(platform != null) platform.draw(g);
			//		if(ball != null) ball.draw(g);


		}else {
			g.setFont(currentFont);
			g.drawString("LevelComplete", (int)(super.getWidth()*.5), (int)(super.getHeight()*.5));
			g.drawString("Points = "+totalpoints,(int)(super.getWidth()*.5), (int)(super.getHeight()*.5)+20);
			g.drawString("Deaths = "+deaths, (int)(super.getWidth()*.5), (int)(super.getHeight()*.5)+40);
		}

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

					if (x.getId().equals("exit")) {
						super.setExit(true);
						this.setExit(true);
					}
					if (availablePlatforms > 0) {
						if (x.getId().equals("platform")) {
							Platform newPlat = new Platform("platform_"+Integer.toString(platforms.size()));
							newPlat.setPosition(500, 400);
							newPlat.setPivotPoint(86, 16);
							//System.out.println(newPlat.getId());
							this.addChild(newPlat);
							platforms.add(newPlat);
							userObjects.add(newPlat);
							availablePlatforms--;

							currentObject = newPlat;
						}
					}
					if(availableFans>0){
						if(x.getId().equals("fan")){
							if(availableFans>0){
								if(x.getId().equals("fan")){
									Fan newFan = new Fan("Fan"+Integer.toString(platforms.size()));
									newFan.setPosition(500, 400);
									newFan.setPivotPoint(250,250);
									//System.out.println(newFan.getId());
									this.addChild(newFan);
									fans.add(newFan);
									userObjects.add(newFan);
									availableFans--;

									currentObject = newFan;



								}
							}}
					}
					if(x.getId().equals("trampoline")){
						if(availableTrampolines>0){
							if(x.getId().equals("trampoline")){
								Trampoline newTramp = new Trampoline("Trampoline"+Integer.toString(platforms.size()));
								newTramp.setPosition(500, 400);
								newTramp.setPivotPoint(86, 16);
								
								this.addChild(newTramp);
								trampolines.add(newTramp);
								userObjects.add(newTramp);
								this.availableTrampolines--;

								currentObject = newTramp;



							}
						}
					}
					if (x.getId().equals("treadmill")) {
						if (this.availableTreadmills > 0) {
							TreadMill newTread = new TreadMill();
							newTread.setPosition(500, 400);
							newTread.setPivotPoint(86, 16);
							newTread.addActionAnimation("run", 0, 23, 1);
							//System.out.println(newTread.getId());
							this.addChild(newTread);
							treadmills.add(newTread);
							userObjects.add(newTread);
							availableTreadmills--;

							currentObject = newTread;

						}
					} else if (x.getId().equals("treadmill_reverse")) {
						if (this.availableReverseTreadmills > 0) {
							ReverseTreadMill newRevTread = new ReverseTreadMill();
							newRevTread.setPosition(500, 400);
							newRevTread.setPivotPoint(86, 16);
							newRevTread.addActionAnimation("run", 0, 23, 1);
							//							System.out.println(newRevTread.getId());
							this.addChild(newRevTread);
							reverseTreadmills.add(newRevTread);
							userObjects.add(newRevTread);
							availableReverseTreadmills--;

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

	//	public static void main(String[] args) {
	//	LevelSix game = new LevelSix();
	//	game.start();
	//	//		game.closeGame();
	//	//		TrampolineTester tramp_game = new TrampolineTester();
	//	//		tramp_game.start();
	//}
}


        
