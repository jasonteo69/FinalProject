import java.awt.image.BufferedImage;

public class Stage {
    private BufferedImage image;
    private String imageFileName;
    private Wizard[] wizard;
    private Boss[] boss;
    private Frame frame;
    public Stage(Frame frame) {
        this.frame = frame;
    }
    public Stage(String stageNum) {
        this.imageFileName = "images/background" + stageNum + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        wizard = new Wizard[2];
        boss = new Boss[2];
        generateWizards();
        generateBoss();
    }
    private void generateWizards() {
        wizard[0] = new Wizard("1");
        wizard[1] = new Wizard("2");
    }
    private void generateBoss() {
        boss[0] = new Boss("dragon1");
        boss[1] = new Boss("dragon2");

    }

    public BufferedImage getImage() {
        return image;
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

    public Wizard[] getWizard() {
        return wizard;
    }

    public void setWizard(Wizard[] wizard) {
        this.wizard = wizard;
    }

    public Boss[] getBoss() {
        return boss;
    }

    public void setBoss(Boss[] boss) {
        this.boss = boss;
    }
}
