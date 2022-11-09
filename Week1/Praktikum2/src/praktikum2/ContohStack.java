import java.util.Stack;

public class ContohStack {
    public static void main(String args[]) {
        Stack stack1 = new Stack();
        Stack<String> stack2 = new Stack<String>();
        stack1.push(4);
        stack1.push("Rudy");
        stack1.push("Chandra");
        stack2.push("D3");
        stack2.push("Teknologi");
        stack2.push("Informasi");
        System.out.println(stack1);
        System.out.println(stack2);
    }
}
