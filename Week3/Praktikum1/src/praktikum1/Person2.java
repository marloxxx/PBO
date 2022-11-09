package praktikum1;

import java.util.Scanner;

public class Person2 {

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
        // Instansiasi sebuah objek dari kelas Manusia
        manusia_SetterGetter objManusia2 = new manusia_SetterGetter();
        objManusia2.setNama(nama);
        objManusia2.setAlamat(alamat);
        objManusia2.setUsia(usia);
        // Invoking cetakInformasi method
        objManusia2.cetakInformasi(objManusia2);
    }
}
