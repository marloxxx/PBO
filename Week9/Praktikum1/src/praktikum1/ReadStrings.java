package praktikum1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadStrings {

    public static void main(String[] args) {
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Masukkan Teks: ");
        System.out.println("Masukkan 'stop' untuk berhenti.");
        try (br) {
            do {
                str = br.readLine();
                System.out.print(str + "\n");
            } while (!str.equals("stop"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
