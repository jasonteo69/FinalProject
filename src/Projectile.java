import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Projectile {
    private BufferedImage image;
    private String imageFileName;
    private boolean isFiring;
    private int x;
    private int y;
    public Projectile(String projectile) {
        this.imageFileName = "images/" + projectile + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        x = 0;
        y = 0;
    }
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
        x += 30;
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
}
