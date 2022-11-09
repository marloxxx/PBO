package library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws bukuKurangEksepsi {
        // define variable to input data from keyboard
        String title, writer, publisher, genre, illustrator, color;
        int publicationYear, numberOfPages, numberOfChapters, numberOfVolumes, numberOfBooks;

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
        input.nextLine();
        System.out.println("Masukkan Genre: ");
        genre = input.nextLine();
        System.out.println("Masukkan Illustrator: ");
        illustrator = input.nextLine();
        System.out.println("Masukkan Warna: ");
        color = input.nextLine();
        System.out.println("Masukkan Jumlah Bab: ");
        numberOfChapters = input.nextInt();
        System.out.println("Masukkan Jumlah Volume: ");
        numberOfVolumes = input.nextInt();
        System.out.println("Masukkan Jumlah Buku: ");
        numberOfBooks = input.nextInt();

        // create object from Komik class
        Comic comic = new Comic();
        comic.setTitle(title);
        comic.setWriter(writer);
        comic.setPublisher(publisher);
        comic.setPublicationYear(publicationYear);
        comic.setNumberOfPages(numberOfPages);
        comic.setGenre(genre);
        comic.setIllustrator(illustrator);
        comic.setColor(color);
        comic.setNumberOfChapters(numberOfChapters);
        comic.setNumberOfVolumes(numberOfVolumes);
        comic.setNumberOfBooks(numberOfBooks);
        // display all data that attributes have in Comic class
        comic.display(comic);

        try {
            System.out.println("Masukkan Jumlah yang dipinjam: ");
            int numberOfBorrowedBooks = input.nextInt();
            comic.pinjamBuku(numberOfBorrowedBooks);
            System.out.println("Buku berhasil dipinjam");
            System.out.println("Jumlah Buku yang tersedia: " + comic.getNumberOfBooks());
        } catch (bukuKurangEksepsi e) {
            System.out.println("Maaf, jumlah buku yang tersedia tidak mencukupi");
            System.out.println("Buku yang yang tersedia : " + e.getJumlahBukuSekarang());
        } finally {
            System.out.println("Terima Kasih");
        }

        // close scanner
        input.close();
        // close program
        System.exit(0);
    }
}
