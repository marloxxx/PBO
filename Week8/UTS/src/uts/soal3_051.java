package uts;

public class soal3_051 {

    int x;

    void binatang(String nama) {
        System.out.println(nama);
    }

    class kucing {

        int y;

        public String suara() {
            String suara = "Meeoong...";
            return suara;
        }
    }

    class kambing {

        public String suara() {
            String suara = "em.biiikk...";
            return suara;
        }
    }

    class anjing {

        public String suara() {
            String suara = "guguk...";
            return suara;
        }
    }

    public static void main(String[] args) {
        soal3_051 binatang = new soal3_051();
        soal3_051.kucing kucing = binatang.new kucing();
        soal3_051.kambing kambing = binatang.new kambing();
        soal3_051.anjing anjing = binatang.new anjing();
        System.out.println("suara kucing " + kucing.suara());
        binatang.binatang("hewan baru yaitu nyamuk");
        System.out.println("suara kambing " + kambing.suara());
        System.out.println("suara anjing " + anjing.suara());
    }
}
