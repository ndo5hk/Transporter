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
		
		//setting normal angle
		if (top_collision) {
			normal = platform_angle + 90;
		} else {
			normal = platform_angle - 90;
		}

		//getting ball_angle
		ball_angle = this.slopeToDegrees(ball.getVelX(), ball.getVelY());
		
		//setting ball_reflection angle
		reflection_angle = (normal-ball_angle) + normal;
		
		//getting speed and setting the resulting x and y vals
		speed = getSpeedFromVelocities(ball.getVelX(), ball.getVelY());
		newX = speed*Math.cos(Math.toRadians(reflection_angle));
		newY = speed*Math.sin(Math.toRadians(reflection_angle));
		
		ArrayList<Double> velocities = new ArrayList<Double>();
		velocities.add(newX);
		velocities.add(newY);
		
		return velocities;
	}
	
	private double slopeToDegrees(double xVel, double yVel) {
		if (xVel == 0.0) {
			if (yVel > 0) {
				return 90.0;
			} else if (yVel < 0) {
				return 270.0;
			} else {
				System.out.println("The ball handled a collision, but had 0 velocity. Something went wrong");
				return -1;
			}
		}
		double slope = yVel/xVel;
		if (Math.toDegrees(Math.atan(slope)) <= 180) {
			return Math.toDegrees(Math.atan(slope))+180;
		}
		return Math.toDegrees(Math.atan(slope))-180;
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
	
	public static void main(String[] args) {
		
	}
	

}
