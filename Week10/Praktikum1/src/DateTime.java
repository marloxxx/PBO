import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class DateTime extends TimerTask {
    @Override
    public void run() {
        System.out.println("Time started at:" + new Date());
        completeTask();
        System.out.println("Time finished at:" + new Date());
    }

    private void completeTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Program Started");
        Timer timer = new Timer();
        TimerTask timerTask = new DateTime();
        timer.scheduleAtFixedRate(timerTask, 0, 10000);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timer.cancel();
            System.out.println("Program cancelled");
        }
    }
}
