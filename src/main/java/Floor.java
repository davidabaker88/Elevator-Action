package main.java;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dbaker on 5/14/2020.
 */
public class Floor {
    protected AnimatedTexture texture;
    protected Rectangle hitbox;

    public Floor(int x, int y){
        texture = new AnimatedTexture("playerWalkLeft");
        hitbox = new Rectangle(x,y,20,20);
    }
    public void Draw(Graphics2D g2d){
        //g2d.drawImage(Image, xpos, ypos, );
        g2d.drawImage(texture.getImage(),hitbox.x,hitbox.y,Main.getInstance());
    }
}
