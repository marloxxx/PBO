package praktikum1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person3 {

    public static void main(String[] args) {
        String _nama, _alamat, _nim;
        int _usia;
        List<Float> _myIps = new ArrayList<>();
        System.out.print("Masukan nama: ");
        _nama = new Scanner(System.in).nextLine();
        System.out.print("Masukan NIM: ");
        _nim = new Scanner(System.in).nextLine();
        System.out.print("Masukan usia: ");
        _usia = Integer.valueOf(new Scanner(System.in).nextLine());
        System.out.print("Alamat: ");
        _alamat = new Scanner(System.in).nextLine();
        for (int i = 0; i < 5; i++) {
            System.out.print(String.format("Masukan IPS ke-%d: ", i));
            _myIps.add(Float.valueOf(new Scanner(System.in).nextLine()));
        }
        System.out.println("=====================================\n");
        // Instansiasi kelas Mahasiswa
        inheritance_mahasiswa objMahasiswa = new inheritance_mahasiswa();
        objMahasiswa.setNama(_nama);
        objMahasiswa.setAlamat(_alamat);
        objMahasiswa.setNim(_nim);
        objMahasiswa.setUsia(_usia);
        // Invoke procedure rekamIpsSaya
        objMahasiswa.rekamIpsSaya(_myIps);
        // Invoke procedure cetakInformasi
        objMahasiswa.cetakInformasi();

    }
}
