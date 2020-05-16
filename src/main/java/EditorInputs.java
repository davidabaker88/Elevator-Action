package main.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Created by dbaker on 4/30/2020.
 */
public class EditorInputs {
	private static TAdapter keyAdapter=new TAdapter();
	private static MAdapter mouseAdapter=new MAdapter();
	private static LevelEditor observer=LevelEditor.getInstance();
	private static Sprite dragSprite=null;
	private static int dragX=0;
	private static int dragY=0;
	private static boolean snapToGrid=true;
	private static int gridSize=10;
	
	public static void init() {
		//add all input adapters to the panel
		observer.addMouseListener(mouseAdapter);
		observer.addMouseMotionListener(mouseAdapter);//need this for mouse moved and dragged and probably entered and exited methods to work
		observer.addMouseWheelListener(mouseAdapter);//need this for mouseWheelMovedMethod to work
		observer.addKeyListener(keyAdapter);
	}
	
	
	private static class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_S:
					if (e.isControlDown()) {
						//save
						LevelEditor.level.save();
						System.out.println("saved");
					}
				break;
				case KeyEvent.VK_RIGHT:
					LevelEditor.level.save();
					System.out.println("saved");
					LevelEditor.level=GameLevel.load(LevelEditor.level.getNo()+1);
				break;
				case KeyEvent.VK_LEFT:
					int nextLevel=LevelEditor.level.getNo()-1;
					if (nextLevel>=0) {
						LevelEditor.level.save();
						System.out.println("saved");
						LevelEditor.level=GameLevel.load(nextLevel);
					}
					
				break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					//stop moving left
				break;
				case KeyEvent.VK_W:
					//stop elevator up (if on elevator) otherwise attempt to go through door
				break;
				case KeyEvent.VK_S:
					//stop elevator down (if on elevator)
				break;
				case KeyEvent.VK_D:
					//stop moving right
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
			if (e.getX()<=observer.getSideBar().getWidth()) {
				dragSprite=observer.getSideBar().getSpriteAt(e.getPoint());
				if (dragSprite!=null) {
					dragX=e.getX()-dragSprite.getHitBox().x;
					dragY=e.getY()-dragSprite.getHitBox().y;
				}
			} else {
				dragSprite=observer.level.getSpriteAt(e.getPoint());
				if (dragSprite!=null) {
					dragX=e.getX()-dragSprite.getHitBox().x;
					dragY=e.getY()-dragSprite.getHitBox().y;
				}
			}
		}
		
		public void mouseReleased(MouseEvent e) {
			if (e.getX()>observer.getSideBar().getWidth()&&dragSprite!=null&&!LevelEditor.level.getSprites().contains(dragSprite)) {
				LevelEditor.level.getSprites().add(dragSprite);
			} else if (e.getX()<=observer.getSideBar().getWidth()&&dragSprite!=null) {
				//remove sprite from level if it is put back in the pallet
				LevelEditor.level.getSprites().remove(dragSprite);
			}
			dragSprite=null;
		}
		
		//probably won't need most of these under this but they're here
		
		public void mouseEntered(MouseEvent e) {
			
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
		
		public void mouseDragged(MouseEvent e) {
			try {
				int newX=e.getX()-dragX;
				int newY=e.getY()-dragY;
				if (snapToGrid) {
					newX=Math.floorDiv(newX, gridSize)*gridSize;
					newY=Math.floorDiv(newY, gridSize)*gridSize;
				}
				dragSprite.setPos(newX, newY);
			} catch (NullPointerException e1) {
				//do nothing
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			/*try {
				dragSprite.setPos(e.getX()-dragX, e.getY()-dragY);
			} catch (NullPointerException e1) {
				//do nothing
			}*/
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {
			
		}
	}
	
	public static void drawDragSprite(Graphics2D g2d) {
		try {
			dragSprite.draw(g2d);
		} catch (NullPointerException e) {
			//do nothing
		}
	}
	
	public static void drawGrid(Graphics g) {
		if (snapToGrid) {
			g.setColor(Color.BLACK);
			for (int i=1;i<=Math.floorDiv(observer.getWidth(), gridSize);i++) {
				g.drawLine(i*gridSize, 0, i*gridSize, observer.getHeight());
			}
			for (int i=1;i<=Math.floorDiv(observer.getHeight(), gridSize);i++) {
				g.drawLine(0, i*gridSize, observer.getWidth(),i*gridSize);
			}
		}
	}
}
