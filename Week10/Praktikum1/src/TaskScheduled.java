import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduled {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                timer.cancel();
            }
        };
        timer.schedule(timerTask, 5000);
        System.out.println("Task scheduled.");
    }
}
