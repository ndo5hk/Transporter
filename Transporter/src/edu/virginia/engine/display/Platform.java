package edu.virginia.engine.display;
import edu.virginia.lab1test.SoundManager;
import java.util.ArrayList;

import edu.virginia.lab1test.SoundManager;

public class Platform extends Sprite {

	
	SoundManager sound_manager;

	public Platform(String id) {
		super(id, "platform.png", false);
		sound_manager = super.getSoundManager();
	}
	
	public void handleCollision(Ball a) {
         //   sound.PlaySoundEffect(playstate);
		System.out.println(Double.toString(a.getVelX()));
		ArrayList<Double> vels = super.getElasticCollisionVels(a, this);
		a.setVelX(vels.get(0));
//		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
//			a.setVelX(150);
//		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
//			a.setVelX(-150);
			//System.out.println("got here");
//		}
		a.setVelY(vels.get(1)*0.4);
	}

}
