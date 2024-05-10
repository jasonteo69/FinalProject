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
        if (wizard.getWizX() >= 2000) {
            wizard.setWizX(0);
        }
        if (wizard.getWizY() >= 1000) {
            wizard.setWizY(0);
        }
    }
    public void updateProjectilePosition() {
        if (projectile.isFiring()) {
            projectile.shoot();
            if (projectile.getX() >= 2500 || !projectile.isShow()) {
                projectile.setFiring(false);
                projectile.setShow(false);
            }
        }
        //collision detection
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
        if (boss.getHealth() <= 0) {
            //next level call
            stage = new Stage("2");
            wizard = stage.getWizard()[1];
            projectile = wizard.getProjectile()[1];
            boss = stage.getBoss()[1];
        }
    }
    //Enemy AI
    public void updateBossPosition() {
        while (boss.getY() < 368 && boss.getHealth() > 0) {
            boss.setY(boss.getY() + 5);
        }

        boss.updateCoords();
        collision.setObject(boss.getHitbox());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.drawImage(stage.getImage(), -100, 0, this);

        //wizard
        wizard.drawWizard(g);
        //wizard health
        wizard.drawHearts(g);

        //projectile
        if (projectile.isShow()) {
            projectile.drawProjectle(g);
        }

        //debugging collision
        //g.drawRect(projectile.getX(), projectile.getY(), 75,75);

        //boss
        g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
        //boss health
        g.setFont(new Font("TT Supermolot Neue", Font.PLAIN, 45));
        g.setColor(new Color(207, 3, 252));
        g.drawString("Health: ", boss.getX(), boss.getY() - 55);
        g.setColor(Color.red);
        g.fillRect(boss.getX() + 150, boss.getY() - 100, (int)(200 * boss.getHealth() / 25), 50);

        //debugging collision
        //g.drawRect(boss.getX() + 50, boss.getY(), boss.getWIDTH(), boss.getHEIGHT());

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
        if (e.getKeyCode() == 32 && projectile.isCanFire()) {
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
