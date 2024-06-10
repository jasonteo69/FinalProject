import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawPanel extends JPanel implements KeyListener, MouseListener {
    private Wizard wizard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Stage stage;
    private Projectile wizardProjectile;
    private Projectile bossProjectile;
    private Boss boss;
    private CollisionHandler[] collision;
    private final Frame gp;
    private int projNum;
    private boolean drawRest;
    private boolean start;

    public DrawPanel (Frame gp) {
        this.gp = gp;
        stage = new Stage("1");
        boss = stage.getBoss()[0];
        wizard = stage.getWizard()[0];
        wizardProjectile = wizard.getProjectile()[0];
        bossProjectile = boss.getProjectile()[0];
        collision = new CollisionHandler[2];
        collision[0] = new CollisionHandler(boss.getHitbox(), wizardProjectile.getHitbox());
        collision[1] = new CollisionHandler(wizard.getHitbox(), bossProjectile.getHitbox());
        projNum = 0;
        start = true;
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
        wizard.updateCoords();
        //setting bounds
        if (wizard.getWizX() >= (int) (Frame.screenWidth * 1.0417)) {
            wizard.setWizX(0);
        }
        if (wizard.getWizY() >= (int) (Frame.screenWidth * 0.5209)) {
            wizard.setWizY(0);
        }
        if (wizard.getWizX() < 0) {
            wizard.setWizX(0);
        }
        if (wizard.getWizY() < 0) {
            wizard.setWizY((int) (Frame.screenHeight * .8));
        }
    }
    public void updateProjectilePosition() {
        if (wizardProjectile.isFiring()) {
            wizardProjectile.shoot(25, "right");
            if (wizardProjectile.getX() >= (int) (Frame.screenWidth * 1.3026) || !wizardProjectile.isShow()) {
                wizardProjectile.setFiring(false);
                wizardProjectile.setShow(false);
            }
        }
        //collision detection
        wizardProjectile.updateCoords();
        collision[0].setProjectile(wizardProjectile.getHitbox());
        updateBossPosition();
        if (collision[0].collided()) {
            wizardProjectile.setX(-1);
            wizardProjectile.setShow(false);
            boss.setHealth(boss.getHealth() - wizard.getDamage());
            wizardProjectile.setShow(false);
        }
    }
    public void manageLevel() {
        if (boss.getHealth() <= 0 && projNum == 0) {
        //next level call
        projNum++;
        gp.setDied(true);
        gp.getLevelScreen().setLevel(gp.getLevelScreen().getLevel() + 1);
        stage = new Stage("2");
        wizard = stage.getWizard()[1];
        wizardProjectile = wizard.getProjectile()[1];
        boss = stage.getBoss()[1];
        bossProjectile = boss.getProjectile()[1];
        collision[0] = new CollisionHandler(boss.getHitbox(), wizardProjectile.getHitbox());
        collision[1] = new CollisionHandler(wizard.getHitbox(), bossProjectile.getHitbox());
    }
        if (boss.getHealth() <= 0) {
            drawRest = false;
            start = false;
        }
    }
    public void updateBossPosition() {
        if (boss.isCanMove()) {
            boss.setY(boss.getY() - 1);
            if (boss.getY() < (int) (Frame.screenHeight * .093)) {
                boss.setCanMove(false);
            }
        } else {
            boss.setY(boss.getY() + 1);
            if (boss.getY() > (int) (Frame.screenHeight * .602)) {
                boss.setCanMove(true);
            }
        }
        boss.updateCoords();
        collision[0].setObject(boss.getHitbox());
    }
    public void bossAttack() {
        boss.shoot(projNum);
        if (bossProjectile.isCanFire()) {
            bossProjectile.setFiring(true);
            bossProjectile.shoot(15, "left");
        }
        if (bossProjectile.getX() <= 0) {
            bossProjectile.setFiring(false);
            bossProjectile.setShow(false);
            bossProjectile.setX(boss.getX() - 150);
            bossProjectile.setY(boss.getY() + 150);
        }
        bossProjectile.updateCoords();
        if (collision[1].collided()) {
            bossProjectile.setShow(false);
            bossProjectile.setFiring(false);
            bossProjectile.setX(boss.getX() - 150);
            bossProjectile.setY(boss.getY() + 150);
            wizard.setHit(true);
            wizard.setHealth(wizard.getHealth() - boss.getDamage());
            if (wizard.getHealth() <= 0) {
                drawRest = false;
            }
        } else {
            wizard.setHit(false);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (!drawRest && start) {
            g.drawImage(gp.getLevelScreen().getImage(), getX(), getY(), Frame.screenWidth, Frame.screenHeight, this);
            gp.getLevelScreen().drawTitle(g);
            gp.getLevelScreen().drawButton1(g);
            gp.getLevelScreen().drawButton2(g);
        } else if (drawRest) {

            //background
            g.drawImage(stage.getImage(), -100, getY(), this);


            //wizard
            wizard.drawWizard(g);
            //wizard health
            wizard.drawHearts(g);


            //projectile
            if (wizardProjectile.isShow()) {
                wizardProjectile.drawProjectle(g);
            }


            //boss
            g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
            //boss health
            boss.drawHealthBar(g);
            //boss projectile
            if (bossProjectile.isShow()) {
                bossProjectile.drawProjectle(g);
            }
        } else {
            g.setColor(Color.red);
            g.fillRect((int) (Frame.screenWidth * .35), (int) (Frame.screenHeight * .3), 450, 250);
            g.setFont(new Font("TT Supermolot Neue", Font.PLAIN, 100));
            g.setColor(Color.darkGray);
            g.drawString("You Win", (int) (Frame.screenWidth * .35), (int) (Frame.screenHeight * .475));
            gp.getLevelScreen().drawButton3(g);
        }
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


    @Override
    public void mouseClicked(MouseEvent e) {
        if (gp.getLevelScreen().getNewGame().contains(e.getX(), e.getY()) && start) {
            drawRest = true;
            start = false;
            stage = new Stage("1");
            boss = stage.getBoss()[0];
            wizard = stage.getWizard()[0];
            wizardProjectile = wizard.getProjectile()[0];
            bossProjectile = boss.getProjectile()[0];
            collision = new CollisionHandler[2];
            collision[0] = new CollisionHandler(boss.getHitbox(), wizardProjectile.getHitbox());
            collision[1] = new CollisionHandler(wizard.getHitbox(), bossProjectile.getHitbox());
            projNum = 0;
            start = true;
            gp.getLevelScreen().setLevel(1);
            projNum = 0;
        }
        if (gp.getLevelScreen().getSavedFile().contains(e.getX(), e.getY()) && gp.getLevelScreen().getLevel() == 2) {
            wizard = stage.getWizard()[1];
            wizardProjectile = wizard.getProjectile()[1];
            boss = stage.getBoss()[1];
            bossProjectile = boss.getProjectile()[1];
            collision[0] = new CollisionHandler(boss.getHitbox(), wizardProjectile.getHitbox());
            collision[1] = new CollisionHandler(wizard.getHitbox(), bossProjectile.getHitbox());
            start = true;
            drawRest = true;
            wizard.setHealth(15);
            wizard.setLimit(3);
            gp.getLevelScreen().loadData(wizard.getWizX(), wizard.getWizY(), boss.getX(), boss.getY());
        }
        if (gp.getLevelScreen().getEndGame().contains(e.getX(), e.getY())) {
            System.exit(74502);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isDrawRest() {
        return drawRest;
    }
}
