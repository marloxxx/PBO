package praktikum1;

public class dana {

    private int jumlahUang;

    public dana(int jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public int getJumlahUang() {
        return jumlahUang;
    }

    public void setJumlahUang(int jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public int mintaSumbangan(int jumlahSumbangan) throws uangKurangEksepsi {
        if (jumlahSumbangan > jumlahUang) {
            jumlahUang -= jumlahSumbangan;
        } else {
            throw new uangKurangEksepsi(jumlahUang);
        }
        return jumlahUang;
    }

    @Override
    public String toString() {
        return "Dana saat ini : " + jumlahUang;
    }
}
