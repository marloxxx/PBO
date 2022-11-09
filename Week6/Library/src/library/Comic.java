package library;

public class Comic extends Book {

    // define all atributes for Komik class
    private String genre;
    private String illustrator;
    private String color;
    private int numberOfChapters;
    private int numberOfVolumes;

    // create a constructor for Komik class
    public Comic() {
    }

    // create getter methode to get all data that attributes have in Komik class
    public String getGenre() {
        return this.genre;
    }

    // create setter methode to set all data that attributes have in Komik class
    public void setGenre(String value) {
        genre = value;
    }

    public String getIllustrator() {
        return this.illustrator;
    }

    public void setIllustrator(String value) {
        illustrator = value;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String value) {
        color = value;
    }

    public int getNumberOfChapters() {
        return this.numberOfChapters;
    }

    public void setNumberOfChapters(int value) {
        numberOfChapters = value;
    }

    public int getNumberOfVolumes() {
        return this.numberOfVolumes;
    }

    public void setNumberOfVolumes(int value) {
        numberOfVolumes = value;
    }

    // create methode to show all data that attributes have in Komik class
    public void display(Object o) {
        System.out.printf("Judul Buku: %s%n", getTitle());
        System.out.printf("Penulis: %s%n", getWriter());
        System.out.printf("Penerbit: %s%n", getPublisher());
        System.out.printf("Tahun Terbit: %s%n", getPublicationYear());
        System.out.printf("Jumlah Halaman: %s%n", getNumberOfPages());
        System.out.printf("Genre: %s%n", getGenre());
        System.out.printf("Ilustrator: %s%n", getIllustrator());
        System.out.printf("Warna: %s%n", getColor());
        System.out.printf("Jumlah Bab: %s%n", getNumberOfChapters());
        System.out.printf("Jumlah Volume: %s%n", getNumberOfVolumes());
        System.out.printf("Jumlah Buku: %s%n", getNumberOfBooks());
    }
}
