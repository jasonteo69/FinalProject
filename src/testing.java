import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;

public class testing {
    public static void main(String[] args) {
        //Define the variables "final" so we can access them from
        //anonymous local classes (i.e. from inside "public void run()")

        final Timer timer = new Timer("LOL");

        timer.scheduleAtFixedRate(new TimerTask() {
            int n = 1;
            @Override
            public void run() {
                System.out.println("hello");
                System.out.println("Scheduled reminder #" + n++);
                System.out.println("You're still sitting on it aren't you?");
            }}, 1500, 1000);
    }
}
