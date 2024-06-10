import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Projectile implements KeyListener {
    private BufferedImage image;
    private String imageFileName;
    private boolean isFiring;
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle hitbox;
    private boolean show;
    private boolean canFire;
    public Projectile(String projectile, int width, int height) {
        this.imageFileName = "images/" + projectile + ".png";
        this.image = Wizard.readImage(imageFileName);
        x = 0;
        y = 0;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(x, y, width, height);
        canFire = true;
        isFiring = false;
    }
    public void drawProjectle(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    public boolean isCanFire() {
        return canFire;
    }
    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isFiring() {
        return isFiring;
    }


    public void setFiring(boolean firing) {
        isFiring = firing;
    }

    public void shoot(int speed, String direction) {
        if (direction.equals("right")) {
            x += speed;
        } else {
            x -= speed;
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void updateCoords() {
        hitbox.setBounds(x, y, width, height);
    }
   public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            isFiring = true;
       } else {
            isFiring = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

}
