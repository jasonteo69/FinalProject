import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss {
    private int health;
    private int damage;
    private String imageFileName;
    private BufferedImage image;
    private Rectangle hitbox;
    public Boss (String boss) {
        health = 100;
        damage = 2;
        this.imageFileName = "images/" + boss + ".png";
        this.image = Wizard.readImage(imageFileName);
        hitbox = new Rectangle(450, 300, 243, 165);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
