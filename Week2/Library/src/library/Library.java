package library;

public class Library {
    // define all atributes for Library class
    private String title;
    private String writer;
    private String publisher;
    private int publicationYear;
    private int numberOfPages;

    // create a constructor for Library class
    public Library(String title, String writer, String publisher, int publicationYear, int numberOfPages) {
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }

    // create display methode to display all data that attributes have in Library
    // class
    public void display() {
        String temp = String.format("Judul : %s \nPenulis : %s\nPenerbit : %s\nTahun Terbit : %s\nJumlah Halaman : %s",
                getTitle(),
                getWriter(), getPublisher(), getPublicationYear(), getNumberOfPages());
        System.out.println(temp);
    }

    // create methode to get all data that attributes have in Library class
    public String getTitle() {
        return this.title;
    }

    public String getWriter() {
        return this.writer;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }
}
