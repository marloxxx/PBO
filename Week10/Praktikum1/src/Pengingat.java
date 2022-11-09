import java.util.Timer;
import java.util.TimerTask;

public class Pengingat {
    Timer timer;

    Pengingat(int detik){
        timer = new Timer();
        MakanTask makantask = new MakanTask();
        timer.schedule(makantask, detik*1000);
    }

    class MakanTask extends TimerTask{
        @Override
        public void run(){
            System.out.println("yuk makan");
            timer.cancel();
        }
    }
}
