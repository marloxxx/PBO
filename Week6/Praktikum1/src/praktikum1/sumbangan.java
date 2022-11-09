package praktikum1;

public class sumbangan {

    public static void main(String[] args) {
        dana myDana = new dana(100000);
        System.out.println(myDana);
        try {
            System.out.println("Meminta Dana : " + myDana.mintaSumbangan(6000));
            System.out.println(myDana);
            System.out.println("Meminta Dana : " + myDana.mintaSumbangan(5000));
            System.out.println(myDana);
        } catch (uangKurangEksepsi e) {
            System.out.println("Maaf Uang saat ini tersisa : " + e.getJumlahUangskrg());
        }
    }
}
