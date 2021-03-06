package main.java;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Viewport {
	private int x;
	private int y;
	private int width;
	private int height;
	private JPanel observer;
	
	public Viewport(JPanel observer) {
		this.observer=observer;
		x=0;
		y=0;
		width=observer.getWidth();
		height=observer.getHeight();
	}
	
	public void update() {
		width=observer.getWidth();
		height=observer.getHeight();
	}
	
	public void setPos(Point p) {
		setPos(p.x,p.y);
	}
	
	public void setPos(int x,int y) {
		this.x=x-(width/2);
		this.y=y-(height/2);
	}
	
	public Rectangle getArea() {
		return new Rectangle(x,y,width,height);
	}
	
	public Point getCenter() {
		return new Point(x+width/2,y+height/2);
	}
	
	public Point getScreenPos(int x,int y) {
		return new Point(x-this.x,y+this.y);//since y is inverted add instead of subtract
	}
	
	public Point getScreenPos(Point p) {
		return getScreenPos(p.x,p.y);
	}
}
