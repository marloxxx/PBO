package praktikum1;

public class uangKurangEksepsi extends Exception {

    private int jumlahUangskrg;

    public uangKurangEksepsi(int jumlahUangskrg) {
        this.jumlahUangskrg = jumlahUangskrg;
    }

    public int getJumlahUangskrg() {
        return jumlahUangskrg;
    }
}
