package praktikum1;

import java.util.Scanner;

public class Latihan_03 {
    public static void main(String[] args) {
        String nama; // variable dengan tipe String
        int umur; // variabel dengan tipe integer
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan nama Anda: ");
        nama = sc.next();
        System.out.println("Masukkan umur Anda: ");
        umur = sc.nextInt();
        System.out.println("Hello, " + nama + ". Umur Anda " + umur);
    }
}
