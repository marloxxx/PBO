package praktikum1;

import javax.swing.*;

public class InsDoWhi {
    public static void main(String[] args) {
        int SS = 0;
        int A = 2;
        int F = 2;
        int I = 2;
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
                I = 2;
                do {
                    F = F * A;
                    SS = SS + (F - 1);
                    ++I;
                } while (I <= Bilang);
            }
        }
        System.out.println("Jumlah Deret = " + SS);
        System.exit(0);
    }
}
