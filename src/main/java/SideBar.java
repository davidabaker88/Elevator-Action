package main.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;

public class SideBar {
	private final JPanel observer;
	private int width;
	private ArrayList< Sprite> sprites;
	
	public SideBar(JPanel observer,int width) {
		this.observer=observer;
		this.width=width;
		sprites=new ArrayList<>();
		sprites.add(new Floor((int)Math.round(width/2d-38/2d),31));
		sprites.add(new Door(false,(int)Math.round(width/2d-64/2d),73));
		sprites.add(new Door(true,(int)Math.round(width/2d-64/2d),142));
	}
	
	public void setWidth(int width) {
		this.width=width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void draw(Graphics g) {
		//draw side bar
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, observer.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, observer.getHeight()-1);
		//draw title box
		g.drawRect(1, 1, width-2, observer.getHeight()-3);
		g.drawRect(3, 3, width-6, 20);
		g.drawLine(2,25, width-2, 25);
		g.drawLine(2,26, width-2, 26);
		//draw "Pallet" in the middle of the side bar title box
		g.setColor(Color.GREEN);
		int pixelLength=(int) g.getFontMetrics().getStringBounds("Pallet", g).getWidth();
		g.drawString("Pallet", (int)Math.round(width/2d-pixelLength/2d), 18);
		//draw available sprites
		Graphics2D g2d=(Graphics2D) g;
		for (Sprite s:sprites) {
			s.draw(g2d);
		}
	}
	
	public Sprite getSpriteAt(Point p) {
		Sprite returnSprite=null;
		for (Sprite s:sprites) {
			if (s.getHitBox().contains(p)) {
				returnSprite=s.clone();
				break;
			}
		}
		return returnSprite;
	}
	
}
