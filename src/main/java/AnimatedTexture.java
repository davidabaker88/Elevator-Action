package main.java;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by dbaker on 4/30/2020.
 */
public class AnimatedTexture implements Serializable{
    private int activeFrame;
    private int maxFrames;
    private final double pace;//how many game frames to update animation 
    private double ticks=0;//how many game frames have passed (used for pacing)
    private String currentAnimationName;

    public AnimatedTexture(String startAnimation){
        activeFrame=0;
        maxFrames=Textures.getMaxFrames(startAnimation);
        currentAnimationName=startAnimation;
        pace=10;//default pace
    }
    
    public AnimatedTexture(String startAnimation,int pace){
        activeFrame=0;
        maxFrames=Textures.getMaxFrames(startAnimation);
        currentAnimationName=startAnimation;
        this.pace=pace;
    }

    public Image getImage(){
    	//System.out.println(currentAnimationName+": "+activeFrame);
        return Textures.getFrame(currentAnimationName,activeFrame);
    }
    
    public void update(double delta) {
    	ticks+=delta;
    	while (ticks>=pace) {
    		ticks-=pace;
    		//advance one frame
    		nextFrame();
    	}
    }
    
    private void nextFrame() {
    	activeFrame++;
    	if (activeFrame>=maxFrames) {
    		activeFrame=0;//restart animation
    	}
    	//System.out.println("next frame: "+activeFrame);
    }
    //walkLeft :0,1,2
    //walkRight:0,1,2
    //Jump:0
}
