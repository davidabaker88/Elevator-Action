package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dbaker on 4/30/2020.
 */
public class GameLevel implements Serializable{
	//make sure that all game objects are serializable and non-serializable objects that are instance or class properties are marked as transient
	//also note that static fields are always considered transient
	private ArrayList<Floor> floors;
	private ArrayList<Door> doors;
	private transient ArrayList<Enemy> enemies;
	private ArrayList<Escalator> escalators;
	private ArrayList<Elevator> elevators;
	private int no;
	
	/**Instantiate an empty game level (for editing only - use load function to load a level)
	 * 
	 * @param no
	 */
	public GameLevel(int no) {
		floors=new ArrayList<>();
		doors=new ArrayList<>();
		enemies=new ArrayList<>();
	}
	
	
	public void save() {
		new File("levels").mkdir();//make levels directory in case it does not exist
		File level = new File("levels/"+no+".dat");
		FileOutputStream fw;
		try {
			fw = new FileOutputStream(level);
			ObjectOutputStream ow=new ObjectOutputStream(fw);
			//write this object into the file
			ow.writeObject(this);
			//close writers
			ow.close();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static GameLevel load(int no) {
		File level = new File("levels/"+no+".dat");
		FileInputStream fi;
		//default return value is an empty level
		GameLevel loadedLevel=new GameLevel(no); 
		try {
			fi = new FileInputStream(level);
			ObjectInputStream oi = new ObjectInputStream(fi);
			//write this object into the file
			loadedLevel = (GameLevel) oi.readObject();
			//close writers
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loadedLevel;
	}
	
	
}
