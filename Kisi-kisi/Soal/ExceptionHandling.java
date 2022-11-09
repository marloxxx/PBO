import java.util.*;

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

        } catch (InputMismatchException e) {

        } catch (ArithmeticException e) {

        }
        scanner.close();
    }
}
