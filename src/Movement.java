import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener {
    private Wizard wizard;

    public Movement () {
        wizard = new Wizard("");
        this.addKeyListener((this));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wizard.getImage(), wizard.getWizardX(), wizard.getWizardY(), this);
    }
    public void moveDown() {
        wizard.setWizardY(wizard.getWizardY() - 2);
    }
    public void moveUp() {
        wizard.setWizardY(wizard.getWizardY() + 2);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            System.out.println("hello");
            moveUp();
        }
        if (e.getKeyChar() == 's') {
            moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }
}
