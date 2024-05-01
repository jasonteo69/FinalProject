import java.awt.image.BufferedImage;

public class Stage {
    private BufferedImage image;
    private String imageFileName;
    private Wizard[] wizard;
    private Boss[] boss;
    private Stage[] stages;
    public Stage(String stageNum) {
        this.imageFileName = "images/background" + stageNum + ".jpg";
        this.image = Wizard.readImage(imageFileName);
        wizard = new Wizard[2];
        boss = new Boss[2];
        stages = new Stage[2];
        generateWizards();
        generateStage();
        generateBoss();
    }
    private void generateWizards() {
        for (int i = 1; i < wizard.length; i++) {
            wizard[i] = new Wizard("" + i);
        }
    }
    private void generateStage() {
        for (int i = 0; i < stages.length; i++) {
            if (i == 0) {
                stages[i] = new Stage("");
                break;
            }
            stages[i] = new Stage("" + i);
        }
    }
    private void generateBoss() {
        for (int i = 0; i < boss.length; i++) {
            if (i == 0) {
                boss[i] = new Boss("");
                break;
            }
            boss[i] = new Boss("" + i);
        }
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
}
