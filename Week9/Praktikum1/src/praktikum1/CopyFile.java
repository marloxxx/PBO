package praktikum1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {

    public static void main(String[] args) throws IOException {
        int i;
        FileReader fin;
        FileWriter fout;
        try {
            fin = new FileReader("inreadfile.txt");
            fout = new FileWriter("outwritefile.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage: CopyFile from .. to ..");
            return;
        }
        try {
            do {
                i = fin.read();
                if (i != -1) {
                    fout.write(i);
                }
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("File Error");
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
