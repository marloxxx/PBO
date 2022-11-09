package praktikum1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadCharacters {

    public static void main(String[] args) throws IOException {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Masukkan 'k' untuk keluar : ");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'k');
    }
}
