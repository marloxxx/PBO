package praktikum1;

import java.util.ArrayList;
import java.util.List;

public class inheritance_mahasiswa extends inheritance_manusia {

    private String nim;
    private float ipk;
    List<Float> listIps = new ArrayList<Float>();

    public inheritance_mahasiswa() {
    }

    public void setNim(String value) {
        nim = value;
    }

    public String getNim() {
        return nim;
    }

    public float getIpk() {
        return ipk;
    }

    protected void rekamIpsSaya(List<Float> pListIps) {
        for (float ips : pListIps) {
            ipk += ips;
        }
        ipk /= pListIps.size();
    }

    // Override prosedur cetakInformasi
    protected void cetakInformasi() {
        System.out.println("Nama:\t" + getNama());
        System.out.println("NIM:\t" + getNim());
        System.out.println("Usia:\t" + getUsia());
        System.out.println("Alamat:\t" + getAlamat());
        System.out.println("IPK:\t" + getIpk());
    }
}
