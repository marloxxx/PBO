package praktikum1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Soal2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String name;
        int total;
        try {
            System.out.println("Masukkan Nama: ");
            name = input.readLine();
            System.out.println("Jumlah mata kuliah : ");
            total = Integer.parseInt(input.readLine());
            String[] courses = new String[total];
            for (int i = 1; i <= total; i++) {
                System.out.println("MK" + i + " : ");
                courses[i - 1] = input.readLine();
            }
            System.out.println("Masukkan Nama: " + name);
            System.out.println("Jumlah mata kuliah : " + total);
            for (int i = 1; i <= total; i++) {
                System.out.println("MK" + i + " : " + courses[i - 1]);
            }
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
