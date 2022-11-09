package praktikum1;

import javax.swing.*;

public class KontrolSwitch {
    public static void main(String[] args) {
        String kriteria = JOptionPane.showInputDialog("Silahkan pilih kriteria (interval nilai Anda):" + "\n"
                + "1. nilai 50 s/d 64" + "\n" + "2. nilai 65 s/d 84" + "\n" + "3. nilai 85 ke atas");
        int pilihanku = Integer.parseInt(kriteria);
        switch (pilihanku) {
            case 1:
                System.out.println("Lulus C");
                break;
            case 2:
                System.out.println("Lulus B");
                break;
            case 3:
                System.out.println("Lulus A");
                break;
            default:
                System.out.println("Kriteria Salah Entry");
                break;
        }
        System.exit(0);
    }
}
