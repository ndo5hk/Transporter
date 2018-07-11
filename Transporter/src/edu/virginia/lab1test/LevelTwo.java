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
import edu.virginia.engine.display.Trampoline;
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
import java.awt.Shape;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LevelTwo extends Level implements MouseListener {

  DisplayObject background;
	private Platform platform;
	private Ball ball;
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private int deaths;
	private int basepoints=20000;
	private int totalpoints;

	private int availablePlatforms;
	private int availableTrampolines;
	private ArrayList<Platform> platforms;
	private ArrayList<Trampoline> trampolines;
	private ArrayList<DisplayObject> icons;
	private DisplayObject platIcon;
	private String playstate = "";
	private DisplayObject currentObject;
	private ArrayList<DisplayObject> userObjects;
	private int spaceClickTime = 0;
	private int wClickTime = 0;
	private int qClickTime = 0;
	private DisplayObject trampIcon;
        boolean exitbool;
	 Font currentFont ;
         Sprite exit;

	public LevelTwo(HashMap<String, Integer> map, int width, int height) {
		super("Level Two: Trampolines", width,height, map);

		init();
	}

	public String getState() {
		return this.playstate;
	}

	public void init() {
            this.exit = new Sprite("exit","cancel.png",false);
		exit.setPosition(960,10);
		exit.setScaleX(.1);
		exit.setScaleY(.1);
		
    currentFont   = new Font("sansserif",1,15);
    this.background = new DisplayObject("background1","maxresdefault.png",false);
    background.setScaleX(1);
    background.setScaleY(1.2);
    background.setAlpha(.5f);
    this.addChild(background);
		this.finalbox = new FinalDestination(super.getWidth()-180,150);
		this.platform = new Platform("platform_0");  //172x32px

		platform.setPivotPoint(86, 16);
		platform.setPosition(super.getWidth()-230, 180);
		platform.setRotation(60);

		//platform.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		reset(ball);
		super.addChild(platform);
		super.addChild(finalbox);//**
		super.addChild(ball);
		totalpoints = basepoints;//**

		availablePlatforms = 2;
		availableTrampolines=2;
		platforms = new ArrayList<Platform>();
		trampolines = new ArrayList<Trampoline>();
		platforms.add(platform);

		//create Icons
		icons = new ArrayList<DisplayObject>();

		trampIcon = new DisplayObject("trampoline","trampoline.png", false);
		/*platIcon.setPosition(100, 20);
		platIcon.setScaleX(0.5);
		platIcon.setScaleY(0.5);
		super.addChild(platIcon);
		icons.add(platIcon);*/

		trampIcon.setPosition(100, 20);
		trampIcon.setScaleX(0.5);
		trampIcon.setScaleY(0.5);
		super.addChild(trampIcon);
		icons.add(trampIcon);
		userObjects = new ArrayList<DisplayObject>();
		playstate = "design";
                addChild(exit);
                icons.add(exit);
	}
  public void setExit(boolean what){
        this.exitbool = what;
        }
        public boolean getExit(){
        return exitbool;
        }
	private void reset(Ball b) {
		b.setPosition(200, 100);
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
				for (Trampoline tramp : trampolines) {
					if (ball.collidesWith(tramp)!=null) {
						tramp.handleCollision(ball,ball.collidesWith(tramp));
						ball.setPosition(old_x, old_y);
					}
				}
				if (ball.collidesWith(finalbox) ) {
					handleCollision(ball, finalbox);
					playstate = "won";
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
		// System.out.println("First: "this.plaforms.Index(currentObject).getRotation());
	}

	/*     private void handleCollision(Ball a, Platform b) {

		//System.out.println(Double.toString(ball.getVelX()));
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
                //System.out.println("First: "+vels.get(0)*0.8);
		a.setVelX(vels.get(0)*0.8);
                //  System.out.print("Next: "+vels.get(0)*0.8);
		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
			a.setVelX(150);
		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
			a.setVelX(-150);
			//System.out.println("got here");
		}
		a.setVelY(vels.get(1)*0.8);
               if((b.getRotation()<=90 &&b.getRotation()>0)||(b.getRotation()>180 &&b.getRotation()<=270)){
              if(a.getVelX()>0){
               a.setVelX(a.getVelX()*-1);
              }
               }
                if((b.getRotation()>90 &&b.getRotation()<=180)||(b.getRotation()>270 &&b.getRotation()<=360)){
              if(a.getVelX()<0){
               a.setVelX( a.getVelX()*-1);
              }

               }
                System.out.println(b.getRotation());
               //  System.out.println("First: "+a.getVelX());

	}
	 */
	//        private void handleCollision(Ball a, Platform b) {
	//		System.out.println(Double.toString(ball.getVelX()));
	//		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
	//		a.setVelX(vels.get(0)*0.8);
	//		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
	//			a.setVelX(150);
	//		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
	//			a.setVelX(-150);
	//			//System.out.println("got here");
	//		}
	//		a.setVelY(vels.get(1)*0.8);
	//	}

	//private void handleCollision(Ball a, Trampoline b, String hitbox_id) {
	//		if (hitbox_id.equals("trampoline_top")) {
	//			System.out.println("Top");
	//			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
	//			a.setVelX(vels.get(0));
	//			a.setVelY(vels.get(1));
	//		} else {
	//			System.out.println("Bottom");
	//			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
	//			a.setVelX(vels.get(0)*0.2);
	//			a.setVelY(vels.get(1)*0.2);
	//		}
	//	}



	//********************
	private void handleCollision(Ball a, FinalDestination b){ //**** handles level completion
		//endgame? listener?  boolean LevelCompleted
		this.LevelCompleted=true;
		super.setCompleted(true);
	}


	@Override
	public void draw(Graphics g){
		if(!LevelCompleted){
			super.draw(g);
      g.setFont(currentFont);
			g.drawString("Points = "+totalpoints,super.getWidth()-150,20);
			g.drawString("Deaths = "+deaths,super.getWidth()-150,35);
			//g.drawString("X "+this.availablePlatforms, 490,35);
			g.drawString("X "+this.availableTrampolines, 200,35);
			Graphics2D g2d =  (Graphics2D)g;
			if(currentObject!=null){
				g2d.draw(this.currentObject.getGlobalHitbox().get(1));
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
	//		LevelTwo game = new LevelTwo();
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
                                            this.setExit(false);
					}
					if(x.getId().equals("trampoline")){
						if(availableTrampolines>0){
							if(x.getId().equals("trampoline")){
								Trampoline newTramp = new Trampoline("Trampoline"+Integer.toString(platforms.size()));
								newTramp.setPosition(500, 400);
								newTramp.setPivotPoint(86, 16);
								System.out.println(newTramp.getId());
								super.addChild(newTramp);



								trampolines.add(newTramp);
								userObjects.add(newTramp);
								availableTrampolines--;

								currentObject = newTramp;



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
