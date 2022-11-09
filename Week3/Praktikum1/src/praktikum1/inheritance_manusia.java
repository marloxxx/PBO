package praktikum1;

public class inheritance_manusia {

    private String nama, alamat;
    private int usia;

    // Constructor public
    public inheritance_manusia() {
    }

    public void setNama(String value) {
        nama = value;
    }

    public void setAlamat(String value) {
        alamat = value;
    }

    public void setUsia(int value) {
        usia = value;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getUsia() {
        return usia;
    }

    protected void cetakInformasi(Object p) {
        System.out.println("Nama : \t" + ((inheritance_manusia) p).nama);
        System.out.println("Usia : \t" + ((inheritance_manusia) p).usia);
        System.out.println("Alamat : \t" + ((inheritance_manusia) p).alamat);
    }
}
