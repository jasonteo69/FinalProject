import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss {
    private int health;
    private int damage;
    private String imageFileName;
    private BufferedImage image;
    private Rectangle hitbox;
    private int x, y;
    private final int WIDTH;
    private final int HEIGHT;
    private boolean canMove;


    public Boss (String boss) {
        health = 30;
        damage = 5;
        this.imageFileName = "images/" + boss + ".png";
        this.image = Wizard.readImage(imageFileName);
        x = 1400;
        y = 650;
        WIDTH = 450;
        HEIGHT = 335;
        hitbox = new Rectangle(x + 100, y, WIDTH, HEIGHT);
        canMove = true;
    }
    public void updateCoords() {
        hitbox.setBounds(x + 50, y, WIDTH, HEIGHT);
    }
    public void drawHealthBar(Graphics g) {
        g.setFont(new Font("TT Supermolot Neue", Font.PLAIN, 45));
        g.setColor(new Color(207, 3, 252));
        g.drawString("Health: ", x, y - 55);
        g.setColor(Color.red);
        g.fillRect(x + 150, y - 100, (int)(200 * health / 25), 50);
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
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
    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
