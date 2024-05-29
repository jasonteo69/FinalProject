import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelScreen {
    private String imageFileName;
    private BufferedImage image;
    private Rectangle hitbox;
    public LevelScreen() {
        this.imageFileName = "images/" + "levelScreen" + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        hitbox = new Rectangle((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) * .4), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()) * .4), 300, 150);
    }
    public BufferedImage getImage() {
        return image;
    }
    public void drawButton1(Graphics g) {
        g.drawRect((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) * .4), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()) * .4), 300, 150);
        g.setColor(Color.orange);
        g.fillRect((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) * .4), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()) * .4),300, 150);
        g.setColor(Color.RED);
        g.setFont(Font.getFont("Times New Roman"));
        g.drawString("Level 1", (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) * .4), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()) * .4));
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
