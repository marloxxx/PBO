package praktikum1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class check_exception {

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("D:/file.txt");
        BufferedReader input = new BufferedReader(file);
        for (int counter = 0; counter < 5; counter++) {
            System.out.println(input.readLine() + "\n");
        }
        input.close();
    }
}
