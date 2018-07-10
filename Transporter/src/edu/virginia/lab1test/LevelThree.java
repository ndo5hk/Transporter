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
import edu.virginia.engine.display.ReverseTreadMill;
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.display.TreadMill;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab1test.FinalDestination;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class LevelThree extends TransporterGame implements MouseListener {

	private Trampoline trampoline;
	private SwingPlatform swing;
	private Ball ball;
	private boolean LevelCompleted;
	private FinalDestination finalbox;
	private int deaths;
	private int basepoints=20000;
	private int totalpoints;
	
	private int availableTreadMills;
	private int availableRevTreadMills;
	private ArrayList<TreadMill> treadmills;
	private ArrayList<ReverseTreadMill> reverseTreadmills;
	private ArrayList<Trampoline> trampolines;
	private ArrayList<SwingPlatform> swings;
	private ArrayList<DisplayObject> icons;
	private DisplayObject treadIcon;
	private DisplayObject treadRevIcon;
	private String playstate = "";
	private DisplayObject currentObject;
	private ArrayList<DisplayObject> userObjects;
	private int spaceClickTime = 0;
	private int wClickTime = 0;
	private int qClickTime = 0;
	
	
	public LevelThree() {
		super("Level Three: Treadmills", 1000,800);
		init();
	}

	public void init() {
		availableTreadMills = 2;
		availableRevTreadMills =1;
		treadmills = new ArrayList<TreadMill>();
		reverseTreadmills = new ArrayList<ReverseTreadMill>();
		trampolines = new ArrayList<Trampoline>();
		swings = new ArrayList<SwingPlatform>();
		this.finalbox = new FinalDestination(this.getMainFrame().getWidth()-200,this.getMainFrame().getHeight()-150);
		this.trampoline = new Trampoline("trampoline_0");  //172x32px
		trampoline.setPivotPoint(86, 16);
		trampoline.setPosition(500, 550);
		trampoline.setRotation(345);
		trampolines.add(trampoline);
		
		this.swing = new SwingPlatform();
		//swing.setPosition(700, 580);
		swing.setPosition(750, 550);
		swings.add(swing);
		this.getMainFrame().addMouseListener(this);

		//trampoline.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		reset(ball);
		super.addChild(trampoline);
		super.addChild(finalbox);//**
		super.addChild(ball);
		super.addChild(swing);
		totalpoints = basepoints;//**

		
		//create Icons
		icons = new ArrayList<DisplayObject>();
		treadIcon = new DisplayObject("treadmill","treadmill_0.png", false);
		treadIcon.setPosition(385, 20);
		treadIcon.setScaleX(0.5);
		treadIcon.setScaleY(0.5);
		
		treadRevIcon = new DisplayObject("treadmill_reverse","treadmill_reverse_0.png", false);
		treadRevIcon.setPosition(550, 20);
		treadRevIcon.setScaleX(0.5);
		treadRevIcon.setScaleY(0.5);
		super.addChild(treadIcon);
		super.addChild(treadRevIcon);
		icons.add(treadIcon);
		icons.add(treadRevIcon);
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
	
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		//totalpoints
		if(!playstate.equals("won")){
			
			swing.swing();
			for (TreadMill tm : treadmills) {
				tm.animate("run");
			}
			for (ReverseTreadMill tm : reverseTreadmills) {
				tm.animate("run");
			}

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
				//Transforming trampoline based on user input
				
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
				if (!currentObject.getId().contains("treadmill")) {
					if (pressedKeys.contains(65)) {
						currentObject.setRotation(currentObject.getRotation()+5);
					}
					if (pressedKeys.contains(83)) {
						currentObject.setRotation(currentObject.getRotation()-5);
						//System.out.println(Double.toString(trampoline.getRotation()));
					}
					//System.out.println(Double.toString(trampoline.getRotation()));
				}
			}
			
			if (playstate.equals("play")) {
				for (Trampoline tramp : trampolines) {
					if (ball.collidesWith(tramp) != null) {
						handleCollision(ball, tramp, ball.collidesWith(tramp));
						ball.setPosition(old_x, old_y);
					}
				}
				for (TreadMill tm : treadmills) {
					if (ball.collidesWith(tm)) {
						handleCollision(ball, tm);
						ball.setPosition(old_x, old_y);
					}
				}
				for (ReverseTreadMill tm : this.reverseTreadmills) {
					if (ball.collidesWith(tm)) {
						handleCollision(ball, tm);
						ball.setPosition(old_x, old_y);
					}
				}
				for (SwingPlatform swing : swings) {
					if (ball.collidesWith(swing)) {
						handleCollision(ball, swing);
						ball.setPosition(old_x+10, old_y);
					}
				}
				if (ball.collidesWith(finalbox) ) {
					handleCollision(ball, finalbox);
					playstate = "won";
				}
			}
				

//			if (ball.collidesWith(trampoline)) {
//				handleCollision(ball, trampoline);
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
			/* Make sure trampoline is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
			//if(trampoline != null) trampoline.update(pressedKeys);
			//if(ball != null) ball.update(pressedKeys);
		}
	}

	private void handleCollision(Ball a, SwingPlatform b) {
//		System.out.println(Double.toString(ball.getVelX()));
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
		a.setVelX(vels.get(0)*0.9);
//		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
//			a.setVelX(150);
//		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
//			a.setVelX(-150);
//			//System.out.println("got here");
//		}
		a.setVelY(vels.get(1)*0.9);
	}
	
	private void handleCollision(Ball a, TreadMill b) {
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
		if (a.getVelY()<=0) {
			a.setVelX(vels.get(0)*0.1);
			a.setVelY(vels.get(1)*0.1);
		} else {
			a.setVelX(vels.get(0)*0.1+200);
			a.setVelY(vels.get(1)*0.1);
		}
	}
	
	private void handleCollision(Ball a, ReverseTreadMill b) {
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
		a.setVelX(vels.get(0)*0.2-200);
		a.setVelY(vels.get(1)*0.2);
	}
	
	private void handleCollision(Ball a, Trampoline b, String hitbox_id) {
		if (hitbox_id.equals("trampoline_top")) {
			System.out.println("Top");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
			a.setVelX(vels.get(0)*0.9);
			a.setVelY(vels.get(1)*1.2);
		} else {
			System.out.println("Bottom");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
			a.setVelX(vels.get(0)*0.2);
			a.setVelY(vels.get(1)*0.2);
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
			g.drawString("X "+this.availableTreadMills, 505,45); //tohere
			g.drawString("X "+this.availableRevTreadMills, 670,45);
			g.drawString("Forward", 415,15);
			g.drawString("Reverse", 585,15);
			Graphics2D g2d =  (Graphics2D)g;
			if (playstate.equals("design") && currentObject != null) {
				g2d.draw(currentObject.getGlobalHitbox().get(0));
			}
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(icons.get(0).getGlobalHitbox().get(0));
//			Graphics2D g2d =  (Graphics2D)g;
//			g2d.draw(this.finalbox.getGlobalHitbox().get(0));
			//		g2d.draw(this.trampoline.getGlobalHitbox());
			//		if(trampoline != null) trampoline.draw(g);
			//		if(ball != null) ball.draw(g);
			
		
		}else {g.drawString("LevelComplete", (int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5));
		g.drawString("Points = "+totalpoints,(int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5)+10);
		g.drawString("Deaths = "+deaths, (int)(this.getMainFrame().getWidth()*.5), (int)(this.getMainFrame().getHeight()*.5)+20);
		}
	}
	public static void main(String[] args) {
		LevelThree game = new LevelThree();
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
					if (x.getId().equals("treadmill")) {
						if (this.availableTreadMills > 0) {
							TreadMill newTread = new TreadMill();
							newTread.setPosition(500, 400);
							newTread.setPivotPoint(86, 16);
							newTread.addActionAnimation("run", 0, 23, 1);
							System.out.println(newTread.getId());
							super.addChild(newTread);
							treadmills.add(newTread);
							userObjects.add(newTread);
							availableTreadMills--;
							
								currentObject = newTread;
							
						}
					} else if (x.getId().equals("treadmill_reverse")) {
						if (this.availableRevTreadMills > 0) {
							ReverseTreadMill newRevTread = new ReverseTreadMill();
							newRevTread.setPosition(500, 400);
							newRevTread.setPivotPoint(86, 16);
							newRevTread.addActionAnimation("run", 0, 23, 1);
//							System.out.println(newRevTread.getId());
							super.addChild(newRevTread);
							reverseTreadmills.add(newRevTread);
							userObjects.add(newRevTread);
							availableRevTreadMills--;
							
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

