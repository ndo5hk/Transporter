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

public class LevelFive extends Level implements MouseListener {
	//Every Level Has These
	private SoundManager sound;
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private Ball ball;
	private int eClickTime = 0;
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
	private boolean ending = false;


	//object setup
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

	//private ArrayList<SwingPlatform> swingarray;
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
	boolean exitbool;
	int old_x;
	int old_y;


	public LevelFive(HashMap<String, Integer> map, int width, int height) {
		super("Level Five: Thread the Needle", width,height, map);
		init();

	}

	public void init() {

		//Universal Init stuff
		currentFont= new Font("sansserif",1,15);
		availableItems = super.getAvailableItems();
		swing = new ArrayList<SwingPlatform>();
		
		this.background = new DisplayObject("background1","back2.png",false);
		background.setScaleX(2);
		background.setScaleY(2);
		background.setAlpha(.5f);
		this.addChild(this.background);

		//create Icons
		icons = new ArrayList<DisplayObject>();
		platIcon = new DisplayObject("platform","platform.png", false);
		platIcon.setPosition(50, 25);
		platIcon.setScaleX(0.5);
		platIcon.setScaleY(0.5);
		if (availableItems.get("platforms") >0) {
			super.addChild(platIcon);
			icons.add(platIcon);
			platIcon.setPosition((icons.size()-1)*175+50, 25);
		}

		trampIcon = new DisplayObject("trampoline","trampoline.png", false);
		trampIcon.setPosition(200, 20);
		trampIcon.setScaleX(0.55);
		trampIcon.setScaleY(0.55);
		if (availableItems.get("trampolines") >0) {
			super.addChild(trampIcon);
			icons.add(trampIcon);
			trampIcon.setPosition((icons.size()-1)*175+50, 20);
		}

		TreadmillIcon = new DisplayObject("treadmill","treadmill_1.png", false);
		TreadmillIcon.setPosition(350, 20);
		TreadmillIcon.setScaleX(0.45);
		TreadmillIcon.setScaleY(0.45);
		if (availableItems.get("treadmills") >0) {
			super.addChild(TreadmillIcon);
			icons.add(TreadmillIcon);
			TreadmillIcon.setPosition((icons.size()-1)*175+50, 20);
		}

		reverseTreadmillIcon = new DisplayObject("treadmill_reverse","treadmill_0.png", false);
		reverseTreadmillIcon.setPosition(500, 20);
		reverseTreadmillIcon.setScaleX(0.45);
		reverseTreadmillIcon.setScaleY(0.45);
		if (availableItems.get("reverseTreadmills") >0) {
			super.addChild(reverseTreadmillIcon);
			icons.add(reverseTreadmillIcon);
			reverseTreadmillIcon.setPosition((icons.size()-1)*175+50, 20);
		}

		FanIcon = new DisplayObject("fan","fan.png", false);
		FanIcon.setPosition(650, 10);
		FanIcon.setScaleX(0.5);
		FanIcon.setScaleY(0.5);
		if (availableItems.get("fans") >0) {
			super.addChild(FanIcon);
			icons.add(FanIcon);
			FanIcon.setPosition((icons.size()-1)*175+50, 10);
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
		sound = new SoundManager();
		
		this.exit = new Sprite("exit","cancel.png",false);
		exit.setPosition(960,10);
		exit.setScaleX(.1);
		exit.setScaleY(.1);


		//place finalstate
		this.finalbox = new FinalDestination(super.getWidth()-200,super.getHeight()-200);


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


		this.swingplatform0=new SwingPlatform();
		this.swingplatform1=new SwingPlatform();
		this.swingplatform2=new SwingPlatform();
		this.swingplatform3=new SwingPlatform();
		this.swingplatform4=new SwingPlatform();
		this.swingplatform5=new SwingPlatform();
		swing.add(swingplatform0);
		swing.add(swingplatform1);
		// swing.add(swingplatform2);
		// swing.add(swingplatform3);
		// swing.add(swingplatform4);
		// swing.add(swingplatform5);


		swingplatform0.setPosition(850,400);
		swingplatform0.setRotation(270);

		swingplatform1.setPosition(650,550);
		swingplatform1.setRotation(270);

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
		platform0.setPosition(400, 800);
		platform0.setRotation(105);
		platform1.setPivotPoint(86, 16);
		platform1.setPosition(357, 640);
		platform1.setRotation(105);
		platform2.setPivotPoint(86, 16);
		platform2.setPosition(314, 480);
		platform2.setRotation(105);
		platform3.setPivotPoint(86, 16);
		platform3.setPosition(253, 245);
		platform3.setRotation(105);
		platform4.setPivotPoint(86, 16);
		platform4.setPosition(157, 150);
		platform4.setRotation(0);
		platform5.setPivotPoint(86, 16);
		platform5.setPosition(80, 150);
		platform5.setRotation(0);
		platform6.setPivotPoint(86, 16);
		platform6.setPosition(360, 320);
		platform6.setRotation(120);


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
		this.addChild(finalbox);
		this.addChild(ball);

		platforms.add(platform0);
		platforms.add(platform1);
		platforms.add(platform2);
		platforms.add(platform3);
		platforms.add(platform4);
		platforms.add(platform5);
		platforms.add(platform6);
		platforms.add(platform7);
		platforms.add(platform8);
		//   platforms.add(platform9);
		//   platforms.add(platform10);


		this.addChild(platform0);
		this.addChild(platform1);
		this.addChild(platform2);
		this.addChild(platform3);
		this.addChild(platform4);
		this.addChild(platform5);
		this.addChild(platform6);
		//    this.addChild(platform7);
		//     this.addChild(platform8);
		//     this.addChild(platform9);
		//    this.addChild(platform10);

		this.addChild(swingplatform0);
		this.addChild(swingplatform1);
		//   this.addChild(swingplatform2);
		//  this.addChild(swingplatform3);
		//  this.addChild(swingplatform4);
		//  this.addChild(swingplatform5);

		icons.add(exit);
		this.addChild(exit);
	}

	private void reset(Ball b) {
		b.setPosition(100, 200);
		b.setPhysics(false);
		b.setVelX(0);
		b.setVelY(0);
	}

	//Universal methods
	
	public void setCurrentScale(DisplayObject current){

		if(current.getScaleX()==1){
			System.out.print("Before:"+current.getScaleX());
			current.setScaleX(  current.getScaleX()*1.2);
			current.setScaleY( current.getScaleY()*1.2);
			current.setPivotPoint((int)(current.getUnscaledWidth()*.5), (int)(current.getUnscaledHeight()*.5));
		}
		//  System.out.print(""current.getScaleX());
	}
	public void setNormalScale(){
		for(DisplayObject x: userObjects){
			x.setScaleX(1);
			x.setScaleY(1);
			x.setPivotPoint((int)(x.getUnscaledWidth()*.5), (int)(x.getUnscaledHeight()*.5));
			System.out.print("NORMAL");
		}
	}

	public String getState() {
		return this.playstate;
	}

	public void setState(String s) {
		this.playstate = s;
		super.state = s;
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.state = this.state;

		for(SwingPlatform x:swing){
			x.swing();
		}
		for (TreadMill tm : treadmills) {
			tm.animate("run");
		}
		for (ReverseTreadMill tm : reverseTreadmills) {
			tm.animate("run");
		}
		//totalpoints
		if(!playstate.equals("won")){

			//click timer
			if (spaceClickTime > 0) {
				spaceClickTime--;
			}
			if (qClickTime > 0) {
				qClickTime--;
			}
			if (eClickTime > 0) {
				eClickTime--;
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
					setNormalScale();
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

				this.setNormalScale();
				this.setCurrentScale(currentObject);
				//Transforming platform based on user input

//Transforming platform based on user input
				
				if(pressedKeys.contains(65)){
					//left arrow
					//if (currentObject.getPosition()[0] >= 4) {
						currentObject.getPosition()[0] -= 4;
					//}
				}
				if(pressedKeys.contains(87)){
					//up arrow
					//if (currentObject.getPosition()[1] >= 4) {
						currentObject.getPosition()[1] -= 4;
					//}
				}
				if(pressedKeys.contains(68)){
					//right arrow
					//if (currentObject.getPosition()[0] <= super.getWidth()-50) {
						currentObject.getPosition()[0] += 4;
					//}
				}
				if(pressedKeys.contains(83)){
					//down arrow
					//if (currentObject.getPosition()[1] <= super.getHeight()-50) {
						currentObject.getPosition()[1] += 4;
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
						this.completed = false;
						super.completed = false;
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
				if(pressedKeys.contains(69) && eClickTime == 0){
					//w cycle through objects forwards
					if (userObjects.size()>0) {
						int new_obj_index = userObjects.indexOf(currentObject)+1;
						if (new_obj_index == userObjects.size()) {
							new_obj_index = 0;
						}
						currentObject = userObjects.get(new_obj_index);
						eClickTime = 10;
					}
				}

				if (pressedKeys.contains(37)) {
					currentObject.setRotation(currentObject.getRotation()+5);
				}
				if (pressedKeys.contains(39)) {
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
						tm.handleCollision(ball);
						ball.setPosition(old_x, old_y);
					}
				}
				for (ReverseTreadMill tm : this.reverseTreadmills) {

					if (ball.collidesWith(tm)) {
						tm.handleCollision(ball);
						ball.setPosition(old_x, old_y);
					}
				}
				for (SwingPlatform swinging : swing) {
					if (ball.collidesWith(swinging)) {
						swinging.handleCollision(ball);
						ball.setPosition(old_x+10, old_y);
						sound.PlaySoundEffect("ball");
						sound.updateClock();
					}
				}
				if (ball.collidesWith(finalbox) || ending) {
					ball.setPhysics(false);
					ball.setVelX(0);
					ball.setVelY(0);
					if (ball.getScaleX() > 0) {
						ending = true;
						ball.setScaleX(ball.getScaleX()-0.02);
						ball.setScaleY(ball.getScaleY()-0.02);
						if (this.ball.getPosition()[0] != this.finalbox.getPosition()[0]) {
							int x = ball.getPosition()[0]+(this.finalbox.getPosition()[0]+225-ball.getPosition()[0])/100;
							int y = ball.getPosition()[1]+(this.finalbox.getPosition()[1]+165-ball.getPosition()[1])/100;
							this.ball.setPosition(x, y);
						}
					} else {
						ending = false;
						this.reset(ball);
						ball.setScaleX(1);
						ball.setScaleY(1);
						handleCollision(ball, finalbox);
						playstate = "won";

					}
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
				this.completed = false;
				super.completed = false;
			}


			// System.out.println(this.ball.getVelY()+ " "+this.ball.getPhysics());
			/* Make sure platform is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
			//if(platform != null) platform.update(pressedKeys);
			//if(ball != null) ball.update(pressedKeys);
		}
	}

	//		private void handleCollision(Ball a, Platform b) {
	//			System.out.println(Double.toString(ball.getVelX()));
	//			ArrayList<Double> vels = super.getElasticCollisionVels(a, b);
	//			a.setVelX(vels.get(0));
	////			if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
	////				a.setVelX(150);
	////			} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
	////				a.setVelX(-150);
	//				//System.out.println("got here");
	////			}
	//			a.setVelY(vels.get(1)*0.4);
	//		}


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
		//back here
		//if(!(this.playstate.equals("won"))){
		super.draw(g);
		g.setFont(currentFont);
		g.drawString("Deaths = "+deaths,super.getWidth()-150,20);
		if (icons.contains(platIcon)) {
			g.drawString("X "+availableItems.get("platforms"), this.platIcon.getPosition()[0]+97,super.getPosition()[1]+38);
		}
		if (icons.contains(this.FanIcon)) {
			g.drawString("X "+availableItems.get("fans"), this.FanIcon.getPosition()[0]+60,super.getPosition()[1]+38);
		}
		if (icons.contains(this.trampIcon)) {
			g.drawString("X "+availableItems.get("trampolines"), this.trampIcon.getPosition()[0]+103,super.getPosition()[1]+38);
		}
		if (icons.contains(this.reverseTreadmillIcon)) {
			g.drawString("X "+availableItems.get("reverseTreadmills"), this.reverseTreadmillIcon.getPosition()[0]+116,super.getPosition()[1]+38);
			g.drawString("Reverse", this.reverseTreadmillIcon.getPosition()[0]+20, super.getPosition()[1] + 17);
		}
		if (icons.contains(this.TreadmillIcon)) {
			g.drawString("X "+availableItems.get("treadmills"), this.TreadmillIcon.getPosition()[0]+116,super.getPosition()[1]+38);
			g.drawString("Forward", this.TreadmillIcon.getPosition()[0]+20, super.getPosition()[1] + 17);
		}

		Graphics2D g2d =  (Graphics2D)g;
//		if (playstate.equals("design") && currentObject != null) {
//			g2d.draw(currentObject.getGlobalHitbox().get(0));
//		}
		//g2d.draw(finalbox.getGlobalHitbox().get(0));
		//			Graphics2D g2d =  (Graphics2D)g;
		//			g2d.draw(icons.get(0).getGlobalHitbox().get(0));
		//			Graphics2D g2d =  (Graphics2D)g;
		//			g2d.draw(this.finalbox.getGlobalHitbox().get(0));
		//		g2d.draw(this.platform.getGlobalHitbox());
		//		if(platform != null) platform.draw(g);
		//		if(ball != null) ball.draw(g);


		//			else {g.drawString("LevelComplete", (int)(super.getWidth()*.5), (int)(super.getHeight()*.5));
		//			g.drawString("Points = "+totalpoints,(int)(super.getWidth()*.5), (int)(super.getHeight()*.5)+10);
		//			g.drawString("Deaths = "+deaths, (int)(super.getWidth()*.5), (int)(super.getHeight()*.5)+20);
		//			}
	}
	//}
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
								Fan newFan = new Fan("fan"+Integer.toString(platforms.size()));
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
								Trampoline newTramp = new Trampoline("trampoline"+Integer.toString(platforms.size()));
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
		if (playstate.equals("design")) {
			Area click = new Area(new Rectangle2D.Double(e.getX()+this.getPosition()[0], e.getY()+this.getPosition()[1]-25, 1, 1));
			//		System.out.println(Integer.toString(e.getX()));
			//		System.out.println(Integer.toString(e.getY()));
			for (DisplayObject x : icons) {
				Area icon = new Area(x.getGlobalHitbox().get(0));
				icon.intersect(click);
				if (!icon.isEmpty()) {
					x.setScaleX(x.getScaleX()*0.95);
					x.setScaleY(x.getScaleY()*0.95);
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (playstate.equals("design")) {
			Area click = new Area(new Rectangle2D.Double(e.getX()+this.getPosition()[0], e.getY()+this.getPosition()[1]-25, 1, 1));
			//		System.out.println(Integer.toString(e.getX()));
			//		System.out.println(Integer.toString(e.getY()));
			for (DisplayObject x : icons) {
				Area icon = new Area(x.getGlobalHitbox().get(0));
				icon.intersect(click);
				if (!icon.isEmpty()) {
					x.setScaleX(x.getScaleX()/0.95);
					x.setScaleY(x.getScaleY()/0.95);
				}
			}
		}
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
