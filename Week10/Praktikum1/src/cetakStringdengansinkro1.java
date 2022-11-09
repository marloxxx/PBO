class TwoString2 {
    synchronized static void print(String str1, String str2) {
        System.out.print(str1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        System.out.println(str2);
    }
}

class PrintStringThread2 implements Runnable {
    Thread thread;
    String str1, str2;

    PrintStringThread2(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        TwoString2.print(str1, str2);
    }
}

public class cetakStringdengansinkro1 {
    public static void main(String[] args) {
        new PrintStringThread2("Hello ", "there.");
        new PrintStringThread2("How are ", "you?");
        new PrintStringThread2("Thank you ", "very much!");
    }
}
