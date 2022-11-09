package praktikum1;

public class NewMain {

    public static void main(String[] args) {
//        TODO code application logic here
        int a = 1;
        int b = 0;
        double res = devide(a, b);
        System.out.println("Hasil " + a + " : " + b + " = " + res);
        System.out.println("Selesai");
    }

    public static double devide(int a, int b) {
        double result = 0;
        System.out.println("Nilai a : " + a + " dan B : " + b);
        try {
            result = a / b;
        } catch (Exception e) {
            System.out.println("Pesan Error : " + e.getMessage());
        } finally {
            System.out.println("Finally Pembagian bagi selesai");
        }
        result = a / b;
        return result;
    }
}
