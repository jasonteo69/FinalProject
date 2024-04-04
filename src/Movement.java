import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener {
    private Wizard wizard;

    public Movement () {
        wizard = new Wizard(1);
    }
    protected void paintComponenet(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(wizard.getImage(), 0, 0, null);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            wizard.setWizardY(wizard.getWizardY() + 2);
        }
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            wizard.setWizardY(wizard.getWizardY() + 2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
