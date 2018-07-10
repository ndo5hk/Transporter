package edu.virginia.engine.display;

import java.util.ArrayList;

public class TransporterGame extends Game {

	public TransporterGame(String gameId, int width, int height) {
		super(gameId, width, height);
	}
	
	/*
	 * @param if top_collision is true, then the ball collided with the top of the platform
	 */
	protected ArrayList<Double> getElasticCollisionVels(Ball ball, DisplayObject platform, boolean top_collision) {
		double ball_angle;
		double reflection_angle;
		double platform_angle = platform.getRotation();
		double normal;
		double speed;
		double newX;
		double newY;
		double offset_slope;
		
		//setting normal angle
		if (top_collision) {
			normal = (platform_angle + 90 +360)%360;
		} else {
			normal = (platform_angle - 90 +360)%360;
		}
		System.out.println("Plat angle: "+Double.toString(platform_angle));
		System.out.println("Normal: "+Double.toString(normal));

		//getting ball_angle
		ball_angle = this.slopeToDegrees(ball.getVelX(), ball.getVelY());
		System.out.println(" in angle: "+Double.toString(ball_angle));
		//setting ball_reflection angle
		reflection_angle = ((normal-ball_angle) + normal+360)%360;
		//System.out.println("angle: "+Double.toString(reflection_angle));
		
		//getting speed and setting the resulting x and y vals
		speed = getSpeedFromVelocities(ball.getVelX(), ball.getVelY());
		System.out.println("speed: " + Double.toString(speed));
		System.out.println("ref angle: " + Double.toString(reflection_angle));
		
		newX = speed*Math.cos(Math.toRadians(reflection_angle));
		newY = speed*Math.sin(Math.toRadians(reflection_angle));
		
		ArrayList<Double> velocities = new ArrayList<Double>();
		velocities.add(newX);
		velocities.add(newY*-1); //accounting for reversing the y velocity
//		System.out.println(Double.toString(newX));
//		System.out.println(Double.toString(newY));
		System.out.println(" out X: " + Double.toString(newX));
		System.out.println(" out Y: " + Double.toString(newY));
		
		return velocities;
	}
	
	private double slopeToDegrees(double xVel, double yVel) {
		System.out.println(" in X: " + Double.toString(xVel));
		System.out.println(" in Y: " + Double.toString(yVel));
		if (xVel == 0.0) {
			if (yVel > 0) {
				return 90.0;
			} else if (yVel < 0) {
				return 270.0;
			} else {
				System.out.println("The ball handled a collision, but had 0 speed. Something went wrong");
				return -1;
			}
		}
		double slope = yVel/xVel;
//		if (Math.toDegrees(Math.atan2(yVel, xVel)) <= 180) {
//			System.out.println("got here");
//			System.out.println((Math.toDegrees(Math.atan2(yVel, xVel))+180)%360);
//			return (Math.toDegrees(Math.atan2(yVel, xVel)))%360;
//		}
		
		if (xVel>0 ) {
			if (yVel<0) {
				//System.out.println(Double.toString(Math.toDegrees(Math.atan(yVel/xVel))*-1));
				return (Math.toDegrees(Math.atan(yVel*-1/xVel))-180+360)%360;
			} else {
				//System.out.println(Double.toString((90-Math.toDegrees(Math.atan(yVel/xVel))-90 + 360)%360));
				return 360-Math.toDegrees(Math.atan(yVel/xVel))-180;
			}
		} else {
			if (yVel<0) {
				//System.out.println(Double.toString(Math.toDegrees(Math.atan(yVel/xVel))+90));
				return (180-Math.toDegrees(Math.atan(yVel/xVel))-180+360)%360;
			} else {
				//System.out.println(Double.toString((Math.toDegrees(Math.atan(yVel/xVel))+180)%360));
				return Math.toDegrees(Math.atan(yVel/xVel*-1));
			}
		}
		
		//System.out.println((Math.toDegrees(Math.atan2(xVel, yVel))+180+360)%360);
		//return (Math.toDegrees(Math.atan2(yVel*-1, xVel))+360)%360;
	}
	
//	private double degreesToSlope(double deg) {
//		return Math.tan(Math.toRadians(deg));
//	}
	
	private double getSpeedFromVelocities(double xVel, double yVel) {
		return Math.sqrt(Math.pow(xVel, 2)+Math.pow(yVel, 2));
	}
	
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
	}
	
	public static void getRefAngle(double ball_angle,  double normal) {
		double angle = ((normal-ball_angle) + normal+360)%360;
		System.out.println(Double.toString(angle));
	}
	
	public static void main(String[] args) {
//		System.out.println(Double.toString(slopeToDegrees(-6,1)));
		getRefAngle(315,360);
	}
	

}
