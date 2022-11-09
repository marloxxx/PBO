package praktikum1;

import javax.swing.*;

public class KontrolIfElseIf {
    public static void main(String[] args) {
        boolean lulus;
        String kriteria;
        String MasukinNilai = JOptionPane.showInputDialog("Nilai UTS?");
        int nilai = Integer.parseInt(MasukinNilai);
        if (nilai >= 50) {
            lulus = true;
            kriteria = "C";
        } else if ((nilai >= 66) && (nilai <= 80)) {
            lulus = true;
            kriteria = "B";
        } else if ((nilai >= 81) && (nilai <= 100)) {
            lulus = true;
            kriteria = "A";
        } else {
            lulus = false;
            kriteria = "Gagal";
        }
        System.out.println("Nilai UTS ku= " + nilai);
        System.out.println("Status Kelulusan= " + lulus);
        System.out.println("Keterangan Kelulusan= " + kriteria);
    }
}
