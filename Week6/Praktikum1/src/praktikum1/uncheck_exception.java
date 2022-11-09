package praktikum1;

public class uncheck_exception {

    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        try {
            double res = divide(a, b);
            System.out.println("Hasil " + a + " : " + b + " = " + res);
        } catch (Exception e) {
            System.out.println("Pesan Error : " + e.getMessage());
        }
        System.out.println("Selesai");
    }

    public static double divide(int a, int b) {
        double result = 0;
        System.out.println("Nilai a : " + a + " dan B : " + b);
        if (b == 0) {
            throw new ArithmeticException("Pembagi nol");
        }
        {
            result = a / b;
        }
        System.out.println("Pembagian bagi selesai");
        return result;
    }
}
