import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LevelScreen {
    private String imageFileName;
    private BufferedImage image;
    private Rectangle newGame;
    private Rectangle savedFile;
    private Rectangle endGame;
    private FileHandler fileHandler;
    private int level;
    public LevelScreen() {
        this.imageFileName = "images/" + "levelScreen" + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        newGame = new Rectangle((int) (Frame.screenWidth * .3), (int) (Frame.screenHeight * .4), 300, 150);
        savedFile = new Rectangle((int) (Frame.screenWidth * .55), (int) (Frame.screenHeight * .4), 300, 150);
        endGame = new Rectangle((int) (Frame.screenWidth * .7), (int) (Frame.screenHeight * .7), 300, 150);
        fileHandler = new FileHandler();
        level = 1;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void drawTitle(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD,50));
        g.setColor(Color.WHITE);
        g.drawString("Super Wizard", (int) (Frame.screenWidth * .4), (int) (Frame.screenHeight * .25));
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
    public void drawButton3 (Graphics g) {
        g.drawRect((int) (Frame.screenWidth * .7), (int) (Frame.screenHeight * .7), 300, 150);
        g.setColor(Color.BLUE);
        g.fillRect((int) (Frame.screenWidth * .7), (int) (Frame.screenHeight * .7),300, 150);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("verdana", Font.BOLD,50));
        g.drawString("GG Champ", (int) (Frame.screenWidth * .7), (int) (Frame.screenHeight * .775));
    }
    public void loadData(int x, int y, int x2, int y2) {
        fileHandler.saveProgress(level, x, y, x2, y2);
    }
    public Rectangle getNewGame() {
        return newGame;
    }
    public Rectangle getSavedFile() {
        return savedFile;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public Rectangle getEndGame() {
        return endGame;
    }
}
