package praktikum1;

import javax.swing.*;

public class KontrolTernaryIfElse {
    public static void main(String[] args) {
        String MasukkanNilai = JOptionPane.showInputDialog("Masukkan nilai Anda: ");
        int Nilaiku = Integer.parseInt(MasukkanNilai);
        String ket = (Nilaiku >= 55) ? "Lulus" : "Tidak Lulus";
        System.out.println("Nilai = " + Nilaiku);
        System.out.println("Anda = " + ket);
        System.exit(0);
    }
}
