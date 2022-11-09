package praktikum1;

public class KontrolIf {
    int nilaiUTS = 55; //misal batas minimal C = 55
    boolean lulus;
    String ket;

    public static void main(String[] args) {
        KontrolIf Nilaiku = new KontrolIf();
/* Nilaiku = Obyek baru yang diciptakan dari class "KontrolIf" */

        int nilaiutsaku = 65;
        if (nilaiutsaku >= Nilaiku.nilaiUTS) {
            Nilaiku.lulus = true;
            Nilaiku.ket = "C";
        }
        System.out.println("Nilai UTS ku= " + nilaiutsaku);
        System.out.println("Status Kelulusan= " + Nilaiku.lulus);
        System.out.println("Keterangan Kelulusan= " + Nilaiku.ket);
    }
}
