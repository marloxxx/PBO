class PrintNameThread extends Thread {
    PrintNameThread(String name) {
        super(name);
        start();
    }

    public void run() {
        String name = getName();
        for (int i = 0; i < 10; i++) {
            System.out.print(name);
        }
    }
}

public class Multi_Thread {
    public static void main(String[] args) {
        PrintNameThread pnt1 = new PrintNameThread("A");
        PrintNameThread pnt2 = new PrintNameThread("B");
        PrintNameThread pnt3 = new PrintNameThread("C");
        PrintNameThread pnt4 = new PrintNameThread("D");
        PrintNameThread pnt5 = new PrintNameThread("E");
    }
}
