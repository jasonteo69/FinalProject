import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Projectile extends Movement implements KeyListener {
    private BufferedImage image;
    private String imageFileName;
    private boolean isFiring;
    private int x;
    public Projectile(String projectile) {
        this.imageFileName = "images/" + projectile + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        x = super.getX() + 10;
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

    public void shoot() {
        if (isFiring) {
            x += 10;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isFiring = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
