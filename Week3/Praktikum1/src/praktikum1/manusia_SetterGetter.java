package praktikum1;

public class manusia_SetterGetter {

    private String nama, alamat;
    private int usia;

    // Constructor public
    public manusia_SetterGetter() {
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
        System.out.println("Nama : \t" + ((manusia_SetterGetter) p).nama);
        System.out.println("Alamat : \t" + ((manusia_SetterGetter) p).alamat);
        System.out.println("Usia : \t" + ((manusia_SetterGetter) p).usia);
    }
}
