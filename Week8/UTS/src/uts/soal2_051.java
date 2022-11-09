package uts;

import java.util.Scanner;

class mahasiswa {

    public mahasiswa() {
    }

    public static void tampilkan_nama(String nama) {
        System.out.println("Nama : " + nama);
    }

    public void tampilkan_nim(String nim) {
        System.out.println("NIM : " + nim);
    }

    public void tampilkan_kelas(String kelas) {
        System.out.println("Kelas : " + kelas);
    }

    public void hitung_skala_nilai(float nilai) {
        if (nilai <= 100 && nilai >= 79.5) {
            System.out.println("Nilai Anda adalah A");
        } else if (nilai <= 79.5 && nilai >= 72) {
            System.out.println("Nilai Anda adalah AB");
        } else if (nilai <= 72 && nilai >= 64.5) {
            System.out.println("Nilai Anda adalah B");
        } else if (nilai <= 64.5 && nilai >= 57) {
            System.out.println("Nilai Anda adalah BC");
        } else if (nilai <= 57 && nilai >= 49.5) {
            System.out.println("Nilai Anda adalah C");
        } else if (nilai <= 49.5 && nilai >= 34) {
            System.out.println("Nilai Anda adalah D");
        } else if (nilai <= 34 && nilai >= 0) {
            System.out.println("Nilai Anda adalah E");
        }
    }
}

public class soal2_051 {

    public static void main(String[] args) {
        String nama, nim, kelas, nilai;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("DATA ke-" + (i + 1));
            System.out.print("Masukkan Nama Anda : ");
            nama = in.nextLine();
            System.out.print("Masukkan Kelas Anda : ");
            kelas = in.nextLine();
            System.out.print("Masukkan NIM Anda : ");
            nim = in.nextLine();
            System.out.print("Masukkan Nilai Anda : ");
            nilai = in.nextLine();
            nilai = nilai.replace(",", ".");

            mahasiswa mhs = new mahasiswa();
            mahasiswa.tampilkan_nama(nama);

            mhs.tampilkan_nim(nim);
            mhs.tampilkan_kelas(kelas);
            mhs.hitung_skala_nilai(Float.parseFloat(nilai));
            System.out.println("===================================");
        }
    }
}
