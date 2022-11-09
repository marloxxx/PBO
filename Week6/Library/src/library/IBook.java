package library;

public interface IBook {

    String getTitle();

    void setTitle(String value);

    String getWriter();

    void setWriter(String value);

    String getPublisher();

    void setPublisher(String value);

    int getPublicationYear();

    void setPublicationYear(int value);

    int getNumberOfPages();

    void setNumberOfPages(int value);

    int getNumberOfBooks();

    void setNumberOfBooks(int value);

    int pinjamBuku(int jumlahPinjaman) throws bukuKurangEksepsi;
}
