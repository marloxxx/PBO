package percobaan_package.del.ac.id.induk;

public class Kendaraan implements IKendaraan {

    private int jlhRoda, dimensiKendaraan, jlhSpion;
    private String warnaKendaraan;

    @Override
    public void setJlhRoda(int value) {
        jlhRoda = value;
    }

    @Override
    public void setDimensiKendaraan(int value) {
        dimensiKendaraan = value;
    }

    @Override
    public void setJlhSpion(int value) {
        jlhSpion = value;
    }

    @Override
    public void setWarnaKendaraan(String value) {
        warnaKendaraan = value;
    }

    @Override
    public void cetakInformasi(Object o) {
        System.out.println("Informasi dari objek " + o.getClass().getName());
        System.out.println("Jumlah roda: " + jlhRoda);
        System.out.println("Dimensi kendaraan: " + dimensiKendaraan);
        System.out.println("Jumlah spion: " + jlhSpion);
        System.out.println("Warna kendaraan: " + warnaKendaraan);
    }

    @Override
    public int getJlhRoda() {
        return jlhRoda;
    }

    @Override
    public int getDimensiKendaraan() {
        return dimensiKendaraan;
    }

    @Override
    public int getJlhSpion() {
        return jlhSpion;
    }

    @Override
    public String getWarnaKendaraan() {
        return warnaKendaraan;
    }

    @Override
    public float kalkulasiPercepatan(int jlhTorsi, float panjangGagang, float rasioGigi) {
        return jlhTorsi * panjangGagang * rasioGigi;
    }
}
