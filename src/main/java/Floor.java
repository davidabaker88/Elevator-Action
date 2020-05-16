package main.java;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by dbaker on 5/14/2020.
 */
public class Floor extends Sprite{
    

    public Floor(int x, int y){
        super("floor");
        hitbox = new Rectangle(x,y,getTexture().getImage().getWidth(null),getTexture().getImage().getHeight(null));
    }
    
    public void draw(Graphics2D g2d){
        //g2d.drawImage(Image, xpos, ypos, );
        g2d.drawImage(getTexture().getImage(),hitbox.x,hitbox.y,null);//Main.getInstance());
    }
}
