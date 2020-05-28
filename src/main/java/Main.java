package main.java;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Main extends JPanel{
    static boolean gameRunning;
    static boolean menuScreen=true;
    private static final double sleepP=.5;
    public static final int TARGET_FPS=60;
    private static Main instance;
    Player player = new Player();
    GameLevel level=GameLevel.load(0);
    Viewport viewport;
    
    public static void main(String[] unicorns) {
        instance = new Main();

        JFrame frame = new JFrame("Ele-Baker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//For Baker this didn't make a difference but it wasn't the default for me so it must be system dependent. Therefore this line is necessary
        Main game = instance;
        game.setDoubleBuffered(true);
        game.setFocusable(true);
        game.setDoubleBuffered(true);
        Inputs.init();//initialize inputs and add them to the panel
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        
        //game.setDoubleBuffered(true);
        gameRunning = true;
        try {
            instance.gameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void gameLoop() throws InterruptedException {
        long lastLoopTime = System.nanoTime();
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;//target nanoseconds/frame
        long lastSleepValue=1000/TARGET_FPS;//last sleep value in milliseconds
        int fps =0;
        double lastFpsTime=0;
        viewport=new Viewport(this);
        level.setViewport(viewport);
        // keep looping round til the game ends
        while (gameRunning)
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
            // update the game logic
            viewport.update();
            update(delta);
            viewport.setPos(viewport.getArea().x, player.getHitBox().y);

            // draw everyting
            //repaint();
            draw();

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

    private  void update(double delta)
    {
        // all time-related values must be multiplied by delta!
        player.update(delta);

    }
    public void draw()
    {
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
    //do drawing stuff
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, getWidth(), getHeight());//paint background over previously drawn things
        Graphics2D g2d=(Graphics2D) g;
        //draw level sprites
        for (Sprite s:level.getScreenSprites()) {
        	s.draw(g2d);
        }
        //draw player last so that it shows up on top
        player.draw(g2d);
        


    }
    public static Main getInstance() {
    	return instance;
    }
}
