package percobaan_package.del.ac.id.induk;

public class Mobil extends Kendaraan {

    private int jlhTorsi, faktorPembagi;
    private float panjangGagang, rasioGigi;

    public Mobil() {
    }

    public void setJlhTorsi(int value) {
        jlhTorsi = value;
    }

    public void setFaktorPembagi(int value) {
        faktorPembagi = value;
    }

    public void setPanjangGagang(float value) {
        panjangGagang = value;
    }

    public void setRasioGigi(float value) {
        rasioGigi = value;
    }

    public int getJlhTorsi() {
        return jlhTorsi;
    }

    @Override
    public float kalkulasiPercepatan(int jlhTorsi, float panjangGagang, float rasioGigi) {
        return super.kalkulasiPercepatan(jlhTorsi, panjangGagang, rasioGigi) / faktorPembagi;
    }

    @Override
    public void cetakInformasi(Object o) {
        System.out.println("==========================================");
        System.out.println(String.format("Mobil saya berwarna %s.", ((Mobil) o).getWarnaKendaraan()));
        System.out.println(String.format("Dimensi mobil tersebut adalah %d meter persegi.", ((Mobil) o).getDimensiKendaraan()));
        System.out.println(String.format("dan dengan percepatan sekitar %.2f km/jam.", kalkulasiPercepatan(jlhTorsi, panjangGagang, rasioGigi)));
        System.out.println(String.format("Mobil tersebut memiliki %d kaca spion dan %d roda.", ((Mobil) o).getJlhSpion(), ((Mobil) o).getJlhRoda()));
        System.out.println("==========================================");
    }
}
