class PrintNameThread2 implements Runnable{
    Thread thread;
    PrintNameThread2 (String name){
        thread = new Thread(this, name);
        thread.start();
    }

    public void run(){
        String name = thread.getName();
        for (int i = 0; i < 100; i++) {
            System.out.print(name);
        }
    }
}
public class Multi_Thread2 {
    public static void main(String[] args) {
        PrintNameThread2 pnt1 = new PrintNameThread2("A");
        PrintNameThread2 pnt2 = new PrintNameThread2("B");
        PrintNameThread2 pnt3 = new PrintNameThread2("C");
        PrintNameThread2 pnt4 = new PrintNameThread2("D");
        PrintNameThread2 pnt5 = new PrintNameThread2("E");
    }
}
