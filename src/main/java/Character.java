package main.java;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Character {
    protected AnimatedTexture texture;
    protected boolean hasHitBox;
    protected LinkedList<Bullet> bullets;
    protected Rectangle hitbox;

    public Character(){
        bullets = new LinkedList<>();
        hasHitBox = true;
        texture = new AnimatedTexture("playerWalkLeft");
        hitbox = new Rectangle(100,100,20,20);
    }
    protected void Crouch(){

    }

    protected void OpenDoor(){

    }
    protected void Move(){

    }
    protected void Stand(){

    }
    protected void GetOnEscalator(){

    }
    protected void Shoot(){

    }
    public void Draw(Graphics2D g2d){
        //g2d.drawImage(Image, xpos, ypos, );
        g2d.drawImage(texture.getImage(),hitbox.x,hitbox.y,Main.getInstance());
    }
}
