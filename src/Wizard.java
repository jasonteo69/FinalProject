import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wizard {
    private int health;
    private int armor;
    private int damage;
    private String weapon;
    private BufferedImage image;
    private Rectangle wizardPosition;
    private String imageFileName;
    private int wizardX;
    private int wizardY;

    public Wizard (int wizNum) {
        health = 0;
        armor = 0;
        damage = 0;
        weapon = "fireball";
        this.imageFileName = "images/wizard" + wizNum + ".png";
        this.image = readImage();
        wizardPosition = new Rectangle (200 ,200, image.getWidth(), image.getHeight());
    }
    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage readImage() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageFileName));
            return image;
        }
        catch (IOException e) {
            return null;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getWizardPosition() {
        return wizardPosition;
    }

    public void setWizardPosition(Rectangle wizardPosition) {
        this.wizardPosition = wizardPosition;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getWizardX() {
        return wizardX;
    }

    public void setWizardX(int wizardX) {
        this.wizardX = wizardX;
    }

    public int getWizardY() {
        return wizardY;
    }

    public void setWizardY(int wizardY) {
        this.wizardY = wizardY;
    }
}
