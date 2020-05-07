package main.java;

import java.util.LinkedList;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Character {
    protected AnimatedTexture texture;
    protected boolean hasHitBox;
    protected LinkedList<Bullet> bullets;

    public Character(){
        bullets = new LinkedList<>();
        hasHitBox = true;
        texture = new AnimatedTexture("playerLeft");
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
}
