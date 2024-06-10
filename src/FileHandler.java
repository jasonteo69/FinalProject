import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class FileHandler {
    public FileHandler() {

    }

    public void saveProgress(int level, int x, int y, int x2, int y2) {
        if (level == 1) {
            try {
                FileWriter myWriter = new FileWriter("levelInfo/currentPosition");
                myWriter.write("" + x + " " + y + ", " + x2 + " " + y2);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if (level == 2) {
            try {
                FileWriter myWriter = new FileWriter("levelInfo/currentPosition");
                myWriter.write("" + x + " " + y + ", " + x2 + " " + y2);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
