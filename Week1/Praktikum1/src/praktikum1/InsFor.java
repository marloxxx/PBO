package praktikum1;

import javax.swing.*;

public class InsFor {
    public static void main(String[] args) {
        int SS = 0;
        int A;
        int F;
        String Bilangan = JOptionPane.showInputDialog("Angka Deret?");
        int Bilang = Integer.parseInt(Bilangan);
        if (Bilang < 1) {
            SS = 0;
        } else {
            if (Bilang == 1) {
                SS = 1;
            } else {
                A = 2;
                SS = 1;
                F = 2;
                for (int I = 2; I <= Bilang; I++) {
                    F = F * A;
                    SS = SS + (F - 1);
                }
            }
        }
        System.out.println("Jumlah Deret = " + SS);
        System.exit(0);
    }
}
