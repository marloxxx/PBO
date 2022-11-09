package percobaan_package.del.ac.id.induk;

public class Sepeda extends Kendaraan {

    public Sepeda() {
    }

    @Override
    public float kalkulasiPercepatan(int jlhTorsi, float panjangGagang, float rasioGigi) {
        return (jlhTorsi == 0 || panjangGagang == 0 || rasioGigi == 0) ? (float) 10.0 : (jlhTorsi * panjangGagang * rasioGigi);
    }

    @Override
    public void cetakInformasi(Object o) {
        System.out.println("==========================================");
        System.out.println(String.format("Sepeda saya berwarna %s.", ((Sepeda) o).getWarnaKendaraan()));
        System.out.println(String.format("Dimensi sepeda tersebut adalah %d meter persegi.", ((Sepeda) o).getDimensiKendaraan()));
        System.out.println(String.format("dan dengan percepatan sekitar %.2f km/jam.", ((Sepeda) o).kalkulasiPercepatan(0, 0, 0)));
        System.out.println(String.format("Sepeda tersebut memiliki %d kaca spion dan %d roda.", ((Sepeda) o).getJlhSpion(), ((Sepeda) o).getJlhRoda()));
        System.out.println("==========================================");
    }
}
