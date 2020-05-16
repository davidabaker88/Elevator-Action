package main.java;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Inputs {
	private static TAdapter keyAdapter=new TAdapter();
	private static MAdapter mouseAdapter=new MAdapter();
	private static Main observer=Main.getInstance();
	
	public static void init() {
		//add all input adapters to the panel
		observer.addMouseListener(mouseAdapter);
		observer.addMouseMotionListener(mouseAdapter);//need this for mouse moved and dragged and probably entered and exited methods to work
		observer.addMouseWheelListener(mouseAdapter);//need this for mouseWheelMovedMethod to work
		observer.addKeyListener(keyAdapter);
	}
	
	
	private static class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if (observer.menuScreen) {
				//start game
				Main.menuScreen=false;
				//or call a function that transitions
			} else {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A:
						//move left
						observer.player.setXVelocity(-1);
					break;
					case KeyEvent.VK_W:
						//elevator up (if on elevator) otherwise attempt to go through door
					break;
					case KeyEvent.VK_S:
						//elevator down (if on elevator)
						
					break;
					case KeyEvent.VK_D:
						//move right
						observer.player.setXVelocity(1);
					break;
					case KeyEvent.VK_SPACE:
						//jump
					break;
					case KeyEvent.VK_SHIFT:
						//crouch
					break;
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					//stop moving left
					observer.player.setXVelocity(0);
				break;
				case KeyEvent.VK_W:
					//stop elevator up (if on elevator) otherwise attempt to go through door
				break;
				case KeyEvent.VK_S:
					//stop elevator down (if on elevator)
				break;
				case KeyEvent.VK_D:
					//stop moving right
					observer.player.setXVelocity(0);
				break;
				case KeyEvent.VK_SHIFT:
					//stop crouching
				break;
			}
		}
		
		public void keyTyped(KeyEvent e) {
			//usually this is used for things involving the character typed so we probably won't need to do anything here
		}
	}
	
	private static class MAdapter extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			
		}
		
		public void mousePressed(MouseEvent e) {
			
		}
		
		public void mouseReleased(MouseEvent e) {
					
		}
		
		//probably won't need most of these under this but they're here
		
		public void mouseEntered(MouseEvent e) {
			
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
		
		public void mouseDragged(MouseEvent e) {
			
		}
		
		public void mouseMoved(MouseEvent e) {
			
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {
			
		}
	}
}
