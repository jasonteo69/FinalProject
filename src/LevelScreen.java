import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LevelScreen {
    private String imageFileName;
    private BufferedImage image;
    private Rectangle newGame;
    private Rectangle savedFile;
    private FileHandler fileHandler;
    private int level;
    public LevelScreen() {
        this.imageFileName = "images/" + "levelScreen" + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        newGame = new Rectangle((int) (Frame.screenWidth * .3), (int) (Frame.screenHeight * .4), 300, 150);
        savedFile = new Rectangle((int) (Frame.screenWidth * .55), (int) (Frame.screenHeight * .4), 300, 150);
        fileHandler = new FileHandler();
        level = 1;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void drawButton1(Graphics g) {
        g.drawRect((int) (Frame.screenWidth * .3), (int) (Frame.screenHeight * .4), 300, 150);
        g.setColor(Color.orange);
        g.fillRect((int) (Frame.screenWidth * .3), (int) (Frame.screenHeight * .4),300, 150);
        g.setColor(Color.RED);
        g.setFont(new Font("verdana", Font.BOLD,50));
        g.drawString("New Game", (int) (Frame.screenWidth * .3), (int) (Frame.screenHeight * .475));
    }
    public void drawButton2(Graphics g) {
        g.drawRect((int) (Frame.screenWidth * .55), (int) (Frame.screenHeight * .4), 300, 150);
        g.setColor(Color.orange);
        g.fillRect((int) (Frame.screenWidth * .55), (int) (Frame.screenHeight * .4),300, 150);
        g.setColor(Color.RED);
        g.setFont(new Font("verdana", Font.BOLD,50));
        g.drawString("Saved File", (int) (Frame.screenWidth * .55), (int) (Frame.screenHeight * .475));
    }

    public void loadData() {
        fileHandler.saveProgress(level);
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

    public Rectangle getNewGame() {
        return newGame;
    }

    public void setNewGame(Rectangle newGame) {
        this.newGame = newGame;
    }

    public Rectangle getSavedFile() {
        return savedFile;
    }

    public void setSavedFile(Rectangle savedFile) {
        this.savedFile = savedFile;
    }
    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }
}
