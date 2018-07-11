/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virginia.lab1test;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;
import java.awt.Rectangle;
import java.awt.Shape;
/**
 *
 * @author owner
 */
public class FinalDestination extends Sprite{
    public FinalDestination() {
           super("final","final.png",false);
this.setScaleX(.2);  
this.setScaleY(.2);
               
//System.out.print("STUFF: "+this.getGlobalHitboxes().size());
	}
     public FinalDestination(int x, int y) {
           super("final","final.png",false);
this.setScaleX(.2);  
this.setScaleY(.2);
 this.setPosition(x, y);
               
//System.out.print("STUFF: "+this.getGlobalHitboxes().size());
	}
     @Override
      public ArrayList<Shape> getGlobalHitbox(){
            ArrayList<Shape> list = new ArrayList<>();
            list.add(getGlobalTransform().createTransformedShape(new Rectangle(300,200,this.getUnscaledWidth()-600,this.getUnscaledHeight()-500)));
            return list;
        }
  
}
