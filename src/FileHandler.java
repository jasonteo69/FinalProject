import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    private DrawPanel dp;
    private static int writeTracker;
    public FileHandler(DrawPanel dp) {
        this.dp = dp;
    }
    public void writeToFile() {
        writeTracker++;
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
