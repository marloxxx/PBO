package library;

public class Book implements IBook {
    // define all atributes for Library class
    private String title;
    private String writer;
    private String publisher;
    private int publicationYear;
    private int numberOfPages;
    private int numberOfBooks;

    // create a constructor for Library class
    public Book() {
    }

    // create getter methode to get all data that attributes have in Library class
    @Override
    public String getTitle() {
        return this.title;
    }

    // create setter methode to set all data that attributes have in Library class
    @Override
    public void setTitle(String value) {
        title = value;
    }

    @Override
    public String getWriter() {
        return this.writer;
    }

    @Override
    public void setWriter(String value) {
        writer = value;
    }

    @Override
    public String getPublisher() {
        return this.publisher;
    }

    @Override
    public void setPublisher(String value) {
        publisher = value;
    }

    @Override
    public int getPublicationYear() {
        return this.publicationYear;
    }

    @Override
    public void setPublicationYear(int value) {
        publicationYear = value;
    }

    @Override
    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    @Override
    public void setNumberOfPages(int value) {
        numberOfPages = value;
    }

    @Override
    public int getNumberOfBooks() {
        return this.numberOfBooks;
    }

    @Override
    public void setNumberOfBooks(int value) {
        numberOfBooks = value;
    }

    @Override
    public int pinjamBuku(int numberOfBooks) throws bukuKurangEksepsi {
        if (this.numberOfBooks > numberOfBooks) {
            this.numberOfBooks -= numberOfBooks;
        } else {
            throw new bukuKurangEksepsi(numberOfBooks);
        }
        return numberOfBooks;
    }
}
