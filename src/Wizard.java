import javax.imageio.ImageIO;
import java.awt.*;
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
    private BufferedImage hearts;
    private int heartXSpacing;
    private int heartYSpacing;
    private Rectangle hitbox;

    public Wizard (String wizNum) {
        health = 15;
        damage = 3;
        weapon = "fireball";
        this.imageFileName = "images/wizard" + wizNum + ".png";
        this.image = readImage(imageFileName);
        hearts = readImage("images/health1.png");
        wizX = 0;
        wizY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * .8);
        heartXSpacing = 10;
        heartYSpacing = 40;
        projectile = new Projectile[2];
        generateProjectiles();
        hitbox = new Rectangle(wizX, wizY, 100, 100);
    }
    private void generateProjectiles() {
        projectile[0] = new Projectile(weapon + "1");
        projectile[1] = new Projectile(weapon + "2");
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
    public void drawHearts(Graphics g) {
        for (int i = 0; i < health; i++) {
            g.drawImage(hearts, heartXSpacing, heartYSpacing, null);
            heartXSpacing += 75;
            if (heartXSpacing > 1000) {
                heartXSpacing = 0;
            }
        }
    }
    public void drawWizard(Graphics g) {
        g.drawImage(image, wizX, wizY, null);
    }

    public int getDamage() {
        return damage;
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
