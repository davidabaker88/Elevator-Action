package main.java;

import java.awt.*;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Character {
	private static AnimatedTexture NULL=new AnimatedTexture("default");
    protected Hashtable<String,AnimatedTexture> textures;
    private String currentAnimation;
    protected boolean hasHitBox;
    protected LinkedList<Bullet> bullets;
    protected Rectangle hitbox;
    
    
    
    public Character(){
        bullets = new LinkedList<>();
        hasHitBox = true;
        textures = new Hashtable<>();
        currentAnimation="null";
        hitbox = new Rectangle(100,100,20,20);
    }
    
    public Character(String startAnimation) {
    	bullets = new LinkedList<>();
        hasHitBox = true;
        textures = new Hashtable<>();
        currentAnimation=startAnimation;
        addTexture(startAnimation);
        hitbox = new Rectangle(100,100,20,20);
    }
    
    public Character(String startAnimation,int startAnimationPace) {
    	bullets = new LinkedList<>();
        hasHitBox = true;
        textures = new Hashtable<>();
        currentAnimation=startAnimation;
        addTexture(startAnimation,startAnimationPace);
        hitbox = new Rectangle(100,100,20,20);
    }
    
    /**adds a texture to the texture hashtable of this sprite with a default pace of 10 ticks per frame
     * 
     * @param textureName
     */
    protected void addTexture(String textureName) {
    	textures.put(textureName, new AnimatedTexture(textureName));
    }
    
    /**adds a texture to the texture hashtable of this sprite with the specified pace (ticks per frame)
     *
     * @param textureName
     * @param pace ticks per frame
     */
    protected void addTexture(String textureName,int pace) {
    	textures.put(textureName, new AnimatedTexture(textureName,pace));
    }
    
    protected AnimatedTexture getTexture() {
    	if (textures.containsKey(currentAnimation)) {
    		return textures.get(currentAnimation);
    	} else {
    		return NULL;
    	}
    }
    
    protected String getTextureName() {
    	return currentAnimation;
    }
    
    protected void setTexture(String textureName) {
    	currentAnimation=textureName;
    }
    
    
    protected void crouch(){

    }

    protected void openDoor(){

    }
    
    protected void move(){

    }
    
    protected void stand(){

    }
    
    protected void getOnEscalator(){

    }
    
    protected void shoot(){

    }
    
    public void draw(Graphics2D g2d){
        //g2d.drawImage(Image, xpos, ypos, );
        g2d.drawImage(getTexture().getImage(),hitbox.x,hitbox.y,Main.getInstance());
    }
}
