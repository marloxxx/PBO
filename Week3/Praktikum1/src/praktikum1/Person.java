package praktikum1;

import java.util.Scanner;

public class Person {

    public static void main(String[] args) {
        String nama, alamat;
        int usia;

        System.out.print("Masukkan nama : ");
        nama = new Scanner(System.in).nextLine();
        System.out.print("Masukkan usia : ");
        usia = Integer.valueOf(new Scanner(System.in).nextLine());
        System.out.print("Masukkan alamat : ");
        alamat = new Scanner(System.in).nextLine();

        System.out.println("===================================");

        // Instansiasi kelas manusia
        // karena kelas manusia memiliki overloading constructor
        // anda dapat memilih constructor mana yang mau digunakan
        // Sesuaikan dengan kebutuhan anda
        manusia mhs = new manusia(nama, alamat, usia);
        mhs.cetakInformasi();
    }
}
