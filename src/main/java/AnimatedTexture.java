package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by dbaker on 4/30/2020.
 */
public class AnimatedTexture {
    private int activeFrame;
    private int maxFrames;
    private String currentAnimationName;

    public AnimatedTexture(String startAnimation){
        activeFrame=0;
        maxFrames=Textures.getMaxFrames(startAnimation);
        currentAnimationName=startAnimation;
    }

    public Image getImage(){
    	System.out.println(currentAnimationName+": "+activeFrame);
        return Textures.GetFrame(currentAnimationName,activeFrame);
    }
    
    public void nextFrame() {
    	activeFrame++;
    	if (activeFrame>=maxFrames) {
    		activeFrame=0;//restart animation
    	}
    }
    //walkLeft :0,1,2
    //walkRight:0,1,2
    //Jump:0
}
