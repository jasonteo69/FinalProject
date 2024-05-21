import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Runnable {
    private DrawPanel drawing;
    private Thread windowThread;
    private boolean died;
    public Frame(String display) {
        super(display);
        drawing = new DrawPanel(this);
        this.add(drawing);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1650, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        /*
        fullscreen --> this.setUndecorated(true);
        */
        this.setLocationRelativeTo(null);
        this.addKeyListener(drawing);
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

        double start = 1000000000 / 60.0; //60 FPS
        double end = System.nanoTime() + start; //Draws 60 times limit

        while (windowThread.isAlive()) {
            //movement
            drawing.updateWizardPosition();
            drawing.updateProjectilePosition();
            drawing.updateBossPosition();
            drawing.bossAttack();
            repaint();
            try {
                double timeLefttoDraw = end - System.nanoTime();
                timeLefttoDraw /= 1000000; //timeLefttoDraw in milliseconds
                Thread.sleep((long) timeLefttoDraw); //sleep counts time in milliseconds
                end += start; //time for next "frame" to be drawn
            } catch (IllegalArgumentException | InterruptedException e) {
                died = true;
                try {
                    windowThread.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }

    public Thread getWindowThread() {
        return windowThread;
    }

    public void setWindowThread(Thread windowThread) {
        this.windowThread = windowThread;
    }

    public boolean isDied() {
        return died;
    }
}
