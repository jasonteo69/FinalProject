import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Runnable {
    private Movement movement;
    private Thread windowThread;

    public Frame (String display) {
        super(display);
        movement = new Movement();
        this.add(movement);
        this.setDefaultCloseOperation(3);
        this.setSize(750, 513);
        this.setLocationRelativeTo(null);
        this.addKeyListener(movement);
        this.setFocusable(true);
        this.setVisible(true);
        startThread();
    }
    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }
    @Override
    public void run() {

        double start = 1000000000/60.0; //60 FPS
        double end = System.nanoTime() + start; //Draws 60 times limit

        while (true) {
            movement.updateWizardPosition();
            movement.updateProjectilePosition();
            repaint();
            try {

                double timeLefttoDraw = end - System.nanoTime();
                timeLefttoDraw /= 1000000; //timeLefttoDraw in milliseconds
                Thread.sleep((long) timeLefttoDraw); //sleep counts time in milliseconds
                end += start; //time for next "frame" to be drawn

            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }

        }
    }
}
