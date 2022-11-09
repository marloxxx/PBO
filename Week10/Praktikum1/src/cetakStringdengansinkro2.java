class TwoString3{
    static void print(String str1, String str2){
        synchronized (TwoString3.class){
            System.out.print(str1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
            }
            System.out.println(str2);
        }
    }
}

class PrintStringThread3 implements Runnable{
    Thread thread;
    String str1, str2;
    TwoString3 ts;

    PrintStringThread3(String str1, String str2, TwoString3 ts){
        this.str1 = str1;
        this.str2 = str2;
        this.ts = ts;
        thread = new Thread(this);
        thread.start();
    }
    public void run(){
        synchronized (ts){
            ts.print(str1, str2);
        }
    }
}
public class cetakStringdengansinkro2 {
    public static void main(String[] args) {
        TwoString3 ts = new TwoString3();
        new PrintStringThread3("Hello ", "there", ts);
        new PrintStringThread3("How are ", "you?", ts);
        new PrintStringThread3("Thank you ", "very much!", ts);
    }
}
