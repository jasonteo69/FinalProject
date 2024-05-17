import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

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
    private Projectile[] projectile;
    private int projectilex;
    private int projectiley;

    public Boss (String boss) {
        health = 30;
        damage = 5;
        this.imageFileName = "images/" + boss + ".png";
        this.image = Wizard.readImage(imageFileName);
        x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * .7292);
        y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * .602);
        WIDTH = 450;
        HEIGHT = 335;
        hitbox = new Rectangle(x + 100, y, WIDTH, HEIGHT);
        canMove = true;
        projectile = new Projectile[2];
        projectilex = x;
        projectiley = y;
        generateProjectiles();
    }

    public int getDamage() {
        return damage;
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
    private void generateProjectiles() {
        projectile[0] = new Projectile("dragonfire", 100, 150);
        projectile[1] = new Projectile("bullet2", 100, 150);
    }
    public void shoot() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                projectilex -= 10;
            }
        };
        Timer timer = new Timer();
        long delay = 3000L;
        timer.schedule(task, delay);
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

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Projectile[] getProjectile() {
        return projectile;
    }

}
