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
    private Rectangle hitbox;
    private boolean hit;
    private int limit;


    public Wizard (String wizNum) {
        health = 15;
        damage = 3;
        weapon = "fireball";
        this.imageFileName = "images/wizard" + wizNum + ".png";
        this.image = readImage(imageFileName);
        hearts = readImage("images/health1.png");
        wizX = (int) (Frame.screenWidth * .05);
        wizY = (int) (Frame.screenHeight * .8);
        projectile = new Projectile[2];
        generateProjectiles();
        hitbox = new Rectangle(wizX, wizY, 100, 100);
        limit = 3;
    }
    private void generateProjectiles() {
        projectile[0] = new Projectile(weapon + "1", 75, 75);
        projectile[1] = new Projectile(weapon + "2", 75, 75);
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
    public void updateCoords() {
        hitbox.setBounds(wizX + 10, wizY, 50, 100);
    }
    public void drawHearts(Graphics g) {
        int spacing = (int) (wizX * .5);
        for (int i = 0; i < limit; i++) {
            g.drawImage(hearts, spacing, wizY - (int) (Frame.screenHeight * .1), null);
            spacing += (int) (Frame.screenWidth * .05);
            if (hit) {
                limit--;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
