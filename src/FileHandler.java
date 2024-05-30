import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    private DrawPanel dp;
    public FileHandler() {

    }
    public void saveProgress(int level) {
        if (level == 1) {
            try {
                FileWriter myWriter = new FileWriter("levelInfo/currentPosition");
                myWriter.write("" + "wizard health, dragon health, ");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if (level == 2) {
            try {
                FileWriter myWriter = new FileWriter("levelInfo/currentPosition");
                myWriter.write("" + "wizard health, dragon health, ");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
