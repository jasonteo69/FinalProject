import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener {
    private Wizard wizard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Stage stage;
    private Projectile projectile;
    private Boss boss;
    private Collision detection;

    public Movement () {
        wizard = new Wizard("1");
        stage = new Stage("");
        projectile = new Projectile("fireball");
        boss = new Boss("dragon");
        projectile.setX(wizard.getWizX() + 30);
        projectile.setY(wizard.getWizY() + 15);
        detection = new Collision(boss.getHitbox(), projectile.getHitbox());
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
        g.drawImage(projectile.getImage(), wizard.getWizX() + 30, wizard.getWizY() + 15, this);
        g.drawImage(boss.getImage(), 500, 300, this);
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
