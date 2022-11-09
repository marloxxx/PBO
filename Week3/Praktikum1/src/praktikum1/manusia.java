package praktikum1;

public class manusia {

    String nama, alamat;
    int usia;

    manusia() {
    }

    public manusia(String nama, String alamat, int usia) {
        this.nama = nama;
        this.alamat = alamat;
        this.usia = usia;
    }

    protected void cetakInformasi() {
        System.out.println("Nama : \t" + nama);
        System.out.println("Alamat : \t" + alamat);
        System.out.println("Usia : \t" + usia);
    }
}
