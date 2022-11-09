package library;

public interface IBook {

    void setTitle(String value);

    void setWriter(String value);

    void setPublisher(String value);

    void setPublicationYear(int value);

    void setNumberOfPages(int value);

    String getTitle();

    String getWriter();

    String getPublisher();

    int getPublicationYear();

    int getNumberOfPages();
}
