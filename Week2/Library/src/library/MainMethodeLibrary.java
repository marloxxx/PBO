package library;

import java.util.Scanner;

public class MainMethodeLibrary {
    public static void main(String[] args) {
        // define variable to input data from keyboard
        String title, writer, publisher;
        int publicationYear, numberOfPages;

        // create scanner to read data from keyboard
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan Judul: ");
        title = input.nextLine();
        System.out.println("Masukkan Penulis: ");
        writer = input.nextLine();
        System.out.println("Masukkan Penerbit: ");
        publisher = input.nextLine();
        System.out.println("Masukkan Tahun Terbit: ");
        publicationYear = input.nextInt();
        System.out.println("Masukkan Jumlah Halaman: ");
        numberOfPages = input.nextInt();

        // create object from Library class
        Library book = new Library(title, writer, publisher, publicationYear, numberOfPages);
        // call display methode to display all data that attributes have in Library
        // class
        book.display();
        // close scanner
        input.close();
        // close program
        System.exit(0);
    }
}
