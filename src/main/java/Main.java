package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;

public class Main  extends JPanel{
    static boolean gameRunning;
    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame("Ele-Baker");
        Main game = new Main();
        frame.add(game);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameRunning = true;
        try {
            game.gameLoop();
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
            Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
        }
    }

    private  void update(double delta)
    {
        // all time-related values must be multiplied by delta!

    }
    private void draw()
    {
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillOval(0, 0, 30, 30);
        g2d.drawOval(0, 50, 30, 30);
        g2d.fillRect(50, 0, 30, 30);
        g2d.drawRect(50, 50, 30, 30);

        g2d.drawImage(ImageObserver);
    }
}
