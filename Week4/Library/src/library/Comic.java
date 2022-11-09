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

    // create setter methode to set all data that attributes have in Komik class
    public void setGenre(String value) {
        genre = value;
    }

    public void setIllustrator(String value) {
        illustrator = value;
    }

    public void setColor(String value) {
        color = value;
    }

    public void setNumberOfChapters(int value) {
        numberOfChapters = value;
    }

    public void setNumberOfVolumes(int value) {
        numberOfVolumes = value;
    }

    // create getter methode to get all data that attributes have in Komik class
    public String getGenre() {
        return this.genre;
    }

    public String getIllustrator() {
        return this.illustrator;
    }

    public String getColor() {
        return this.color;
    }

    public int getNumberOfChapters() {
        return this.numberOfChapters;
    }

    public int getNumberOfVolumes() {
        return this.numberOfVolumes;
    }

    // create methode to show all data that attributes have in Komik class
    public void display(Object o) {
        System.out.println(String.format("Judul Buku: %s", getTitle()));
        System.out.println(String.format("Penulis: %s", getWriter()));
        System.out.println(String.format("Penerbit: %s", getPublisher()));
        System.out.println(String.format("Tahun Terbit: %s", getPublicationYear()));
        System.out.println(String.format("Jumlah Halaman: %s", getNumberOfPages()));
        System.out.println(String.format("Genre: %s", getGenre()));
        System.out.println(String.format("Ilustrator: %s", getIllustrator()));
        System.out.println(String.format("Warna: %s", getColor()));
        System.out.println(String.format("Jumlah Bab: %s", getNumberOfChapters()));
        System.out.println(String.format("Jumlah Volume: %s", getNumberOfVolumes()));
    }
}
