import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drawing extends JPanel implements KeyListener {
    private Wizard wizard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Stage stage;
    private Projectile projectile;
    private Boss boss;
    private Collision detection;

    public Drawing () {
        wizard = new Wizard("1");
        stage = new Stage("");
        projectile = new Projectile("fireball");
        boss = new Boss("dragon");
        detection = new Collision(boss.getHitbox(), projectile.getHitbox());
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
    }
    public void updateProjectilePosition() {
        if (projectile.isFiring()) {
            projectile.shoot();
            if (projectile.getX() >= 750) {
                projectile.setFiring(false);
                projectile.show = false;
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.drawImage(stage.getImage(), 0, 0, this);

        //wizard
        if (wizard.getWizX() >= 750) {
            wizard.setWizX(0);
        }
        if (wizard.getWizY() >= 470) {
            wizard.setWizY(0);
        }
        g.drawImage(wizard.getImage(), wizard.getWizX(), wizard.getWizY(), this);

        //projectile
        g.drawRect(projectile.getX(), projectile.getY(), 25,25);
        if (projectile.isShow()) {
            g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), this);
        }

        //boss
        g.drawImage(boss.getImage(), 450, 300, this);
        g.drawRect(450, 300, 243, 165);

        detection.setProjectile(projectile.getHitbox());
        detection.setObject(boss.getHitbox());
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
            projectile.show = true;
            projectile.setX(wizard.getWizX());
            projectile.setY(wizard.getWizY());
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
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

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Collision getDetection() {
        return detection;
    }

    public void setDetection(Collision detection) {
        this.detection = detection;
    }
}
