package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import main.java.*;
import javax.swing.ImageIcon;

/**
 * Created by dbaker on 4/30/2020.
 * @author David
 * @author Keegan
 */
public final class Textures {
    private static Hashtable<String,ArrayList<Image>> textureMap;
    private static Textures textures= new Textures();
    private Textures(){
    	textureMap = new Hashtable<>();

        //set up all of the animations here
    	Hashtable<String, Integer> ntf=new Hashtable<>();//names to frames (string is animation name int is frame number
    	//ntf.put("<hashName> <fileName>",<number of frames>) given that hashName and fileName are different
    	//ntf.put("<hashName/fileName>",<number of frames>) given that hashName and fileName are the same
    	ntf.put("playerWalkLeft player/walkLeft", 2);//put hashtable name then path (or if they are the same then just put the one thing)
    	ntf.put("playerWalkRight player/walkRight",2);
    	ntf.put("playerStand player/stand",1);
    	ntf.put("floor",1);
    	// . . .  . . .  //
    	
    	//go through each animation and add images
        for (String name:ntf.keySet()) {//load 3 frames
        	ArrayList<Image> images=new ArrayList<>();
        	Argument a=Argument.getArgs(name);
        	//fileName is the path of the file to be used for loading the image (presuming that the path is assets/sprites/(pathName)/(frameNo).png
        	String fileName;
        	//hashName is the key to be used with 
        	String hashName;
        	//determine hashName and fileName based on the amount of args in the name string
        	if (a.raw().length<2) {
        		hashName=a.cmd();
        		fileName=a.cmd();
        	} else {
        		hashName=a.get(0);
        		fileName=a.get(1);
        	}
        	//load images
        	for (int i=0;i<ntf.get(name);i++) {
        		images.add(loadImage(fileName,ntf.get(name)));
        	}
        	//add imageList to texture map with proper hashName
			try {
				textureMap.put(hashName, images);
			}catch (Exception e){
				System.out.println(e);
			}

		}
    }
    
    public static Image GetFrame(String animationName,int frame){
        return textureMap.get(animationName).get(frame);
    }
    
    public static int getMaxFrames(String animationName) {
    	return textureMap.get(animationName).size();
    }
    
    /**Loads an image from assets/sprites/<code>name</code>/<code>no</code>.png
     * 
     * @param name
     * @param no frame number
     * @return image of sprite
     */
    private static Image loadImage(String name,int no) {
    	return new ImageIcon("assets/sprites/"+name+"/"+no+".png").getImage();
    }
    
    /**Loads an image from assets/<code>directory</code>/<code>name</code>/<code>no</code>.png
     * 
     * @param directory
     * @param name frame number
     * @param no
     * @return image
     */
    private static Image loadImage(String directory,String name,int no) {
    	return new ImageIcon("assets/"+directory+"/"+name+".png").getImage();
    }
}
