class TwoString {
    static void print(String str1, String str2) {
        System.out.print(str1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        System.out.println(str2);
    }
}

class PrintStringThread implements Runnable {
    Thread thread;
    String str1, str2;

    PrintStringThread(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        TwoString.print(str1, str2);
    }
}

public class cetakStringtanpasinkro {
    public static void main(String[] args) {
        new PrintStringThread("Hello ", "there");
        new PrintStringThread("How are ", "you?");
        new PrintStringThread("Thank you ", "very much!");
    }
}
