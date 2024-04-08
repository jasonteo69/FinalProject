import java.awt.image.BufferedImage;

public class Stage {
    private BufferedImage image;
    private String imageFileName;
    public Stage(String stageNum) {
        this.imageFileName = "images/background" + stageNum + ".jpg";
        this.image = Wizard.readImage(imageFileName);
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
