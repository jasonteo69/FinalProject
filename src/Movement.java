import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener {
    private Wizard wizard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Stage stage;
    private Projectile projectile;

    public Movement () {
        wizard = new Wizard("1");
        stage = new Stage("");
        projectile = new Projectile("fireball");
        projectile.setX(wizard.getWizX() + 30);
        projectile.setY(wizard.getWizY() + 15);
    }
    public void updateWizardPosition() {
        if (upPressed) {
            wizard.setWizY(wizard.getWizY() - 5);
        } else if (downPressed) {
            wizard.setWizY(wizard.getWizY() + 5);
        } else if (rightPressed) {
            wizard.setWizX(wizard.getWizX() + 5);
        } else if (leftPressed) {
            wizard.setWizX(wizard.getWizX() - 5);
        }
    }
    public void updateProjectilePosition() {
        if (projectile.isFiring()) {
            projectile.shoot();
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(stage.getImage(), 0, 0, this);
        g.drawImage(wizard.getImage(), wizard.getWizX(), wizard.getWizY(), this);
        g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), this);
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
        if (e.getKeyCode() == 32) {
            projectile.setFiring(true);
        } else {
            projectile.setFiring(false);
        }
    }
    public Projectile getProjectile() {
        return projectile;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }
}
