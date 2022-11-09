package library;

public class bukuKurangEksepsi extends Exception {

    private final int jumlahBukuSekarang;

    public bukuKurangEksepsi(int jumlahBukuSekarang) {
        this.jumlahBukuSekarang = jumlahBukuSekarang;
    }

    public int getJumlahBukuSekarang() {
        return jumlahBukuSekarang;
    }
}
