package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelEditor extends JPanel{
	private static LevelEditor instance;
	static boolean editorRunning;
	private static SideBar sideBar;
	private static final double sleepP=.5;
	public static GameLevel level=GameLevel.load(0);
    public static final int TARGET_FPS=60;
    
    
    public static void main(String[] unicornsThatEditLevels) {
    	instance=new LevelEditor();
    	sideBar=new SideBar(instance,100);
    	JFrame frame = new JFrame("Ele-Baker Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//For Baker this didn't make a difference but it wasn't the default for me so it must be system dependent. Therefore this line is necessary
        LevelEditor game = instance;
        game.setDoubleBuffered(true);
        game.setFocusable(true);
        game.setDoubleBuffered(true);
        EditorInputs.init();//initialize inputs and add them to the panel
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        
        
        //game.setDoubleBuffered(true);
        editorRunning = true;
        try {
            instance.editorLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public void editorLoop() throws InterruptedException {
		long lastLoopTime = System.nanoTime();
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;//target nanoseconds/frame
        long lastSleepValue=1000/TARGET_FPS;//last sleep value in milliseconds
		while (editorRunning)
        {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            /* This is just for fps counter  // update the frame counter
	            lastFpsTime += updateLength;
	            fps++;
	
	            // update our FPS counter if a second has passed since
	            // we last recorded
	            if (lastFpsTime >= 1000000000)
	            {
	                System.out.println("(FPS: "+fps+")");
	                lastFpsTime = 0;
	                fps = 0;
	            }
            */


            // draw everyting
            repaint();
            

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            long error=updateLength - OPTIMAL_TIME;//update Length and Optimal_Time both measured in nanoseconds
            //debug output
            //output info in ms
            //System.out.println("updateLength: "+updateLength/1000000);
            //System.out.println("OPTIMAL_TIME: "+OPTIMAL_TIME/1000000);
            //System.out.println("Error: "+error/1000000);
            //output info in ns
            //System.out.println("updateLength: "+updateLength);
            //System.out.println("OPTIMAL_TIME: "+OPTIMAL_TIME);
            //System.out.println("Error: "+error);
            long proportionalOutput = Math.round(error*sleepP/1000000);//multiply error by P value and convert to milliseconds
            long sleepTime=lastSleepValue-proportionalOutput;//adjust sleep value accordingly
            lastSleepValue=sleepTime;//update last sleep value
            if (sleepTime <0){
                sleepTime = 0;
            }
            //System.out.println("update");

			Thread.sleep( sleepTime );

        }
	}
	
	public static LevelEditor getInstance() {
		return instance;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
    	g.fillRect(0, 0, getWidth(), getHeight());//paint background over previously drawn things
        Graphics2D g2d=(Graphics2D) g;
        //draw grid
        EditorInputs.drawGrid(g);
        //draw level
        level.draw(g2d);
        //draw side bar
        sideBar.draw(g);
        EditorInputs.drawDragSprite(g2d);
	}
	
	public SideBar getSideBar() {
		return sideBar;
	}
}
