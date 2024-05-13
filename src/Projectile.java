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
    private Rectangle hitbox;
    private boolean show;
    private boolean canFire;
    public Projectile(String projectile) {
        this.imageFileName = "images/" + projectile + ".png";
        this.image = Wizard.readImage(imageFileName);
        x = 30;
        y = 0;
        hitbox = new Rectangle(x, y,75, 75);
        canFire = true;
    }
    public void drawProjectle(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public boolean isFiring() {
        return isFiring;
    }

    public int getY() {
        return y;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }

    public void shoot() {
        x += 25;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
    public void updateCoords() {
        hitbox.setBounds(x, y, 25, 25);
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
}
