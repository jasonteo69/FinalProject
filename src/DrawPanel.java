import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawPanel extends JPanel implements KeyListener {
    private Wizard wizard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Stage stage;
    private Projectile wizardProjectile;
    private Projectile bossProjectile;
    private Boss boss;
    private CollisionHandler collision;
    private int screenWidth;
    private int screenHeight;
    private Frame gp;

    public DrawPanel (Frame gp) {
        this.gp = gp;
        screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        stage = new Stage("1");
        boss = stage.getBoss()[0];
        wizard = stage.getWizard()[0];
        wizardProjectile = wizard.getProjectile()[0];
        bossProjectile = boss.getProjectile()[0];
        collision = new CollisionHandler();
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
        if (wizard.getWizX() >= (int) (screenWidth * 1.0417)) {
            wizard.setWizX(0);
        }
        if (wizard.getWizY() >= (int) (screenWidth * 0.5209)) {
            wizard.setWizY(0);
        }
    }
    public void updateProjectilePosition() {
        if (wizardProjectile.isFiring()) {
            wizardProjectile.shoot(25, "right");
            if (wizardProjectile.getX() >= (int) (screenWidth * 1.3026) || !wizardProjectile.isShow()) {
                wizardProjectile.setFiring(false);
                wizardProjectile.setShow(false);
            }
        }
        //collision detection
        wizardProjectile.updateCoords();
        collision.setProjectile(wizardProjectile.getHitbox());
        updateBossPosition();
        if (collision.collided()) {
            wizardProjectile.setX(-1);
            wizardProjectile.setShow(false);
            boss.setHealth(boss.getHealth() - wizard.getDamage());
            wizardProjectile.setShow(false);
            wizard.setHealth(wizard.getHealth() - boss.getDamage());
            System.out.println(wizard.getHealth());
        }
        if (boss.getHealth() <= 0) {
            //next level call
            if (!gp.getWindowThread().isAlive()) {
                gp.startThread();
            }
            stage = new Stage("2");
            wizard = stage.getWizard()[1];
            wizardProjectile = wizard.getProjectile()[1];
            boss = stage.getBoss()[1];
        }
    }
    //Enemy AI
    public void updateBossPosition() {
        if (boss.isCanMove()) {
            boss.setY(boss.getY() - 1);
            if (boss.getY() < (int) (screenHeight * .093)) {
                boss.setCanMove(false);
            }
            } else {
            boss.setY(boss.getY() + 1);
            if (boss.getY() > (int) (screenHeight * .602)) {
                boss.setCanMove(true);
            }
        }
        boss.updateCoords();
        collision.setObject(boss.getHitbox());
    }
    public void bossAttack() {
        bossProjectile.shoot(15, "left");
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.drawImage(stage.getImage(), getX(), getY(), this);

        //wizard
        wizard.drawWizard(g);
        //wizard health
        wizard.drawHearts(g);

        //projectile
        if (wizardProjectile.isShow()) {
            wizardProjectile.drawProjectle(g);
        }

        //debugging collision
        //g.drawRect(projectile.getX(), projectile.getY(), 75,75);

        //boss
        g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
        //boss health
        boss.drawHealthBar(g);
        //boss projectile
        bossProjectile.drawProjectle(g);

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
        if (e.getKeyCode() == 32 && wizardProjectile.isCanFire()) {
            wizardProjectile.setFiring(true);
            wizardProjectile.setShow(true);
            wizardProjectile.setX(wizard.getWizX() + 10);
            wizardProjectile.setY(wizard.getWizY());
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

}
