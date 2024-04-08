import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener {
    private Wizard wizard;
    private int wizardX;
    private int wizardY;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public Movement () {
        wizard = new Wizard("1");
        wizardX = 0;
        wizardY = 0;
    }
    public void updatePosition() {
        if (upPressed) {
            wizardY -= 5;
        } else if (downPressed) {
            wizardY += 5;
        } else if (rightPressed) {
            wizardX += 5;
        } else if (leftPressed) {
            wizardX -= 5;
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wizard.getImage(), wizardX, wizardY, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }
}
