package main.java;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class Main extends JPanel{
    static boolean gameRunning;
    static boolean menuScreen=true;
    //still need to make this actually hold the instance after Baker pushes
    private static Main instance;
    Player player = new Player();
    Floor floor1 = new Floor(100,200);
    public static void main(String[] unicorns) {
        instance = new Main();

        JFrame frame = new JFrame("Ele-Baker");
        Main game = new Main();
        frame.add(game);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gameRunning = true;
        try {
            instance.gameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void gameLoop() throws InterruptedException {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int fps =0;
        double lastFpsTime=0;

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
            update(delta);

            // draw everyting
            draw();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            long sleepTime = System.nanoTime()-lastLoopTime - OPTIMAL_TIME/1000000;
            if (sleepTime <0){
                sleepTime = 0;
            }
            Thread.sleep( sleepTime );
        }
    }

    private  void update(double delta)
    {
        // all time-related values must be multiplied by delta!
        player.Update(delta);

    }
    private void draw()
    {
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
    //do drawing stuff
        Graphics2D g2d=(Graphics2D) g;
        player.Draw(g2d);
        floor1.Draw(g2d);


    }
    public static Main getInstance() {
    	return instance;
    }
}
