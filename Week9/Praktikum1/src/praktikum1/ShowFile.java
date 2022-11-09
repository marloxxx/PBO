package praktikum1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile {

    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream in;
        try {
            in = new FileInputStream("inreadfile.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: ShowFile File");
            return;
        }
        do {
            i = in.read();
            if (i != -1) {
                System.out.print((char) i);
            }
        } while (i != -1);
        in.close();
    }
}
