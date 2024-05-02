import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wizard {
    private int health;
    private int damage;
    private String weapon;
    private BufferedImage image;
    private String imageFileName;
    private int wizX;
    private int wizY;
    private Projectile[] projectile;

    public Wizard (String wizNum) {
        health = 25;
        damage = 5;
        weapon = "fireball";
        this.imageFileName = "images/wizard" + wizNum + ".png";
        this.image = readImage(imageFileName);
        wizX = 0;
        wizY = 375;
        projectile = new Projectile[2];
        generateProjectiles();
    }
    private void generateProjectiles() {
        projectile[0] = new Projectile(weapon + "1");
        projectile[1] = new Projectile(weapon + "2");
    }
    public BufferedImage getImage() {
        return image;
    }

    public static BufferedImage readImage(String imageName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageName));
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getWizX() {
        return wizX;
    }

    public int getWizY() {
        return wizY;
    }

    public void setWizX(int wizX) {
        this.wizX = wizX;
    }

    public void setWizY(int wizY) {
        this.wizY = wizY;
    }

    public Projectile[] getProjectile() {
        return projectile;
    }

}
