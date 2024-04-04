import javax.swing.*;

public class Frame extends JFrame implements Runnable {
    private Movement movement;
    private Thread windowThread;

    public Frame (String display) {
        super(display);
        movement = new Movement();
        this.add(movement);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        this.setVisible(true);
        startThread();
    }
    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }
    @Override
    public void run() {
        while (true) {
            movement.repaint();
        }
    }
}
