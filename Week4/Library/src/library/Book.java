package library;

public class Book implements IBook {

    // define all atributes for Library class
    private String title;
    private String writer;
    private String publisher;
    private int publicationYear;
    private int numberOfPages;

    // create a constructor for Library class
    public Book() {
    }

    // create setter methode to set all data that attributes have in Library class
    @Override
    public void setTitle(String value) {
        title = value;
    }

    @Override
    public void setWriter(String value) {
        writer = value;
    }

    @Override
    public void setPublisher(String value) {
        publisher = value;
    }

    @Override
    public void setPublicationYear(int value) {
        publicationYear = value;
    }

    @Override
    public void setNumberOfPages(int value) {
        numberOfPages = value;
    }

    // create getter methode to get all data that attributes have in Library class
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getWriter() {
        return this.writer;
    }

    @Override
    public String getPublisher() {
        return this.publisher;
    }

    @Override
    public int getPublicationYear() {
        return this.publicationYear;
    }

    @Override
    public int getNumberOfPages() {
        return this.numberOfPages;
    }
}
