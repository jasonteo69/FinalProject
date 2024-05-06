import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawPanel extends JPanel implements KeyListener {
    private Wizard wizard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Stage stage;
    private Projectile projectile;
    private Boss boss;
    private Collision collision;

    public DrawPanel () {
        stage = new Stage("1");
        boss = stage.getBoss()[0];
        wizard = stage.getWizard()[0];
        projectile = wizard.getProjectile()[0];
        collision = new Collision();
    }
    public void updateWizardPosition() {
        //diagonal movement
        if (upPressed && rightPressed) {
            wizard.setWizX(wizard.getWizX() + 4);
            wizard.setWizY(wizard.getWizY() - 4);
        } else if (upPressed && leftPressed) {
            wizard.setWizY(wizard.getWizY() - 4);
            wizard.setWizX(wizard.getWizX() - 4);
        } else if (downPressed && rightPressed) {
            wizard.setWizX(wizard.getWizX() + 4);
            wizard.setWizY(wizard.getWizY() + 4);
        } else if (downPressed && leftPressed) {
            wizard.setWizX(wizard.getWizX() - 4);
            wizard.setWizY(wizard.getWizY() + 4);
        } else { //one direction movement
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
        //setting bounds
        if (wizard.getWizX() >= 750) {
            wizard.setWizX(0);
        }
        if (wizard.getWizY() >= 470) {
            wizard.setWizY(0);
        }
    }
    public void updateProjectilePosition() {
        if (projectile.isFiring()) {
            projectile.shoot();
            if (projectile.getX() >= 750 || !projectile.isShow()) {
                projectile.setFiring(false);
                projectile.setShow(false);
            }
        }
        projectile.updateCoords();
        collision.setProjectile(projectile.getHitbox());
        updateBossPosition();
        if (collision.collided()) {
            projectile.setX(-1);
            projectile.setShow(false);
            boss.setHealth(boss.getHealth() - wizard.getDamage());
            projectile.setShow(false);
            System.out.println(boss.getHealth());
        }
    }
    //Enemy AI
    public void updateBossPosition() {
        while (boss.getY() < 368) {
            boss.setY(boss.getY() + 5);
        }
            boss.setY(boss.getY() - 5);

        boss.updateCoords();
        collision.setObject(boss.getHitbox());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.drawImage(stage.getImage(), 0, 0, this);

        //wizard
        g.drawImage(wizard.getImage(), wizard.getWizX(), wizard.getWizY(), this);
        for (int i = 0; i < wizard.getHealth(); i++) {
            g.drawImage(wizard.getHearts(), 10 + i, 10, this);
        }
        //projectile
        if (projectile.isShow()) {
            g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), this);
        }
        //debugging collision
        g.drawRect(projectile.getX(), projectile.getY(), 25,25);

        //boss
        g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
        //debugging collision
        g.drawRect(boss.getX(), boss.getY(), boss.getWIDTH(), boss.getHEIGHT());

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
        if (e.getKeyCode() == 32) {
            projectile.setFiring(true);
            projectile.setShow(true);
            projectile.setX(wizard.getWizX() + 10);
            projectile.setY(wizard.getWizY());
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

}
