package main.java;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Door extends Sprite{
    private boolean isRed;

    public Door(boolean isRed,int x,int y) {
    	if (isRed) {
    		addTexture("doorClosedRed");
    		addTexture("doorOpenRed");
    		setTexture("doorClosedRed");
    	} else {
    		addTexture("doorClosed");
    		addTexture("doorOpen");
    		setTexture("doorClosed");
    	}
        this.isRed = isRed;
        hitbox = new Rectangle(x,y,getTexture().getImage().getWidth(null),getTexture().getImage().getHeight(null));
    }
    
    public void draw(Graphics2D g2d){
        //g2d.drawImage(Image, xpos, ypos, );
        g2d.drawImage(getTexture().getImage(),hitbox.x,hitbox.y,null);//Main.getInstance());
    }

}
