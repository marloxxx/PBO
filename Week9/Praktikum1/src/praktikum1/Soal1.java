package praktikum1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Soal1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String name, address, phone;
        try {
            System.out.print("Masukkan nama: ");
            name = input.readLine();
            System.out.print("Masukkan alamat: ");
            address = input.readLine();
            System.out.print("Masukkan nomor telepon: ");
            phone = input.readLine();
            System.out.println("Hallo " + name + ", alamatmu di " + address + "\nNomor teleponmu adalah " + phone);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
