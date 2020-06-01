package main.java;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Hashtable;

public class Sprite implements Serializable{
	private static AnimatedTexture NULL=new AnimatedTexture("default");
    protected Hashtable<String,AnimatedTexture> textures;
    private String currentAnimation;
    protected Rectangle hitbox;
    
    public Sprite() {
    	currentAnimation="null";
    	textures = new Hashtable<>();
    }
    
    public Sprite(String startAnimation) {
    	currentAnimation=startAnimation;
    	textures = new Hashtable<>();
        
        addTexture(startAnimation);
    }
    
    public Sprite(String startAnimation,int startAnimationPace) {
    	textures = new Hashtable<>();
        currentAnimation=startAnimation;
        addTexture(startAnimation,startAnimationPace);
    }
    
    /**adds a texture to the texture hashtable of this sprite with a default pace of 10 ticks per frame
     * 
     * @param textureName
     */
    protected void addTexture(String textureName) {
    	textures.put(textureName, new AnimatedTexture(textureName));
    }
    
    protected AnimatedTexture getTexture() {
    	if (textures.containsKey(currentAnimation)) {
    		return textures.get(currentAnimation);
    	} else {
    		return NULL;
    	}
    }
    
    /**adds a texture to the texture hashtable of this sprite with the specified pace (ticks per frame)
    *
    * @param textureName
    * @param pace ticks per frame
    */
   protected void addTexture(String textureName,int pace) {
   	textures.put(textureName, new AnimatedTexture(textureName,pace));
   }
   
   
   
   protected String getTextureName() {
   	return currentAnimation;
   }
   
   protected void setTexture(String textureName) {
   	currentAnimation=textureName;
   }
   
   public void draw(Graphics2D g,Viewport vp) {
	   //override this method
   }
   
   public Rectangle getHitBox() {
	   return hitbox;
   }
   
   public Sprite clone() {
	   /*try {
		   
		   return (Sprite) super.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	   Sprite clone=(Sprite) Cloner.Clone(this);
	   return clone;
   }
   
   public void setPos(int x,int y) {
	   hitbox.x=x;
	   hitbox.y=y;
   }
   
   public Point getScreenPos(Viewport vp) {
	   return vp.getScreenPos(hitbox.x, hitbox.y);
   }
}
