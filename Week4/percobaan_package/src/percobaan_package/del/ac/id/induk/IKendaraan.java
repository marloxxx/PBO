package percobaan_package.del.ac.id.induk;

public interface IKendaraan {

    void setJlhRoda(int value);

    void setDimensiKendaraan(int value);

    void setJlhSpion(int value);

    void setWarnaKendaraan(String value);

    void cetakInformasi(Object o);

    int getJlhRoda();

    int getDimensiKendaraan();

    int getJlhSpion();

    float kalkulasiPercepatan(int jlhTorsi, float panjangGagang, float rasioGigi);

    String getWarnaKendaraan();
}
