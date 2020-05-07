package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by dbaker on 4/30/2020.
 */
public final class Textures {
    private static Hashtable<String,ArrayList<Image>> textureMap;
    private Textures(){
        //set up all of the animations here
        ArrayList<Image> playerWalkLeft = new ArrayList<>();
        //add all of the playerWalkLeft frames
        textureMap.put("playerWalkLeft",playerWalkLeft);
    }
    public static Image GetFrame(String animationName,int frame){
        return textureMap.get(animationName).get(frame);
    }
}
