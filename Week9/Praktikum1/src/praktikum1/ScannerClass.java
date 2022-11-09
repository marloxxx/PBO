import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScannerClass {
    public static void main(String[] args) {
        System.out.println("Bagian ini menggunakan BufferedReader");
        BufferedReader aa = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Namamu siapa? ");
        String bd = "";
        try {
            bd = aa.readLine();
        } catch (Exception e) {
        }
        int a = 0;
        System.out.print("umurmu? ");
        try {
            a = Integer.parseInt(aa.readLine());
        } catch (Exception e) {
        }

        System.out.println("Halo " + bd);
        System.out.println("your age: " + a);
        System.out.println("This is part of Scanner Class");
        Scanner baca = new Scanner(System.in);
        System.out.print("Your name ");
        String nama = baca.nextLine();
        System.out.print("Your money ");
        int mata = baca.nextInt();
        System.out.println("Halo " + nama + " you have " + mata + " rupiah");
    }
}
