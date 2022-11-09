package praktikum1;

public class KontrolIfElse {
    int nilaiJava = 55; // misal batas minimal C = 55
    boolean lulus;
    String ket;

    public static void main(String[] args) {
        KontrolIfElse HasilUjian = new KontrolIfElse();
        /* HasilUjian = Obyek baru yang diciptakan dari class "KontrolIfElse" */

        int nilaiJavaAku = 45;
        if (nilaiJavaAku >= HasilUjian.nilaiJava) {
            HasilUjian.lulus = true;
            HasilUjian.ket = "C";
        } else {
            HasilUjian.lulus = false;
            HasilUjian.ket = "Gagal";
        }
        System.out.println("Nilai Aku= " + nilaiJavaAku);
        System.out.println("Status Kelulusan= " + HasilUjian.lulus);
        System.out.println("Keterangan Kelulusan= " + HasilUjian.ket);
    }
}
