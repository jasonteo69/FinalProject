import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


public class Frame extends JFrame implements Runnable {
    private DrawPanel drawing;
    private Thread windowThread;
    private boolean died;
    public static final int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    public static final int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    private LevelScreen levelScreen;

    public Frame(String display) {
        super(display);
        drawing = new DrawPanel(this);
        this.add(drawing);
        levelScreen = new LevelScreen();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1650, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       /*
       fullscreen --> this.setUndecorated(true);
       */
        this.setLocationRelativeTo(null);
        this.addKeyListener(drawing);
        this.addMouseListener(drawing);
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

        double start = 1000000000 / 60.0; //30 FPS
        double end = System.nanoTime() + start; //Draws 60 times limit


        while (windowThread.isAlive()) {
            //movement
            if (drawing.isDrawRest()) {
                drawing.updateWizardPosition();
                drawing.updateProjectilePosition();
                drawing.updateBossPosition();
                drawing.bossAttack();
            }
            repaint();
            try {
                double timeLefttoDraw = end - System.nanoTime();
                timeLefttoDraw /= 1000000; //timeLefttoDraw in milliseconds
                Thread.sleep((long) timeLefttoDraw); //sleep counts time in milliseconds
                end += start; //time for next "frame" to be drawn
            } catch (IllegalArgumentException | InterruptedException e) {
                if (died && levelScreen.getLevel() > 2) {
                    System.exit(0);
                } else {
                    if (died) {
                        run();
                    }
                }
            }
        }
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    public LevelScreen getLevelScreen() {
        return levelScreen;
    }

    public void setLevelScreen(LevelScreen levelScreen) {
        this.levelScreen = levelScreen;
    }
}

