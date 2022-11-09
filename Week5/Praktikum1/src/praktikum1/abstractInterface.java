package praktikum1;

abstract class fakul {

    public String nama_fakul;
    public int jlh_prodi;

    public fakul(String nama_fakul, int jlh_prodi) {
        this.nama_fakul = nama_fakul;
        this.jlh_prodi = jlh_prodi;
    }

    public abstract void jenisprodi();
}

interface thnfak {

    public void tahun(int tahun);

    public void skill();
}

class Del extends fakul implements thnfak {

    int thn;
    String[] prodi = new String[jlh_prodi];

    public Del(String nama_fakul, int jlh_prodi) {
        super(nama_fakul, jlh_prodi);
    }

    @Override
    public void jenisprodi() {
        prodi[0] = "Teknologi Informasi";
        prodi[1] = "Teknologi Komputer";
        prodi[2] = "Teknologi RPL";
        for (int i = 0; i < jlh_prodi; i++) {
            System.out.println("Prodi " + (i + 1) + " : " + prodi[i]);
        }
    }

    @Override
    public void skill() {
        System.out.println("System Analyst");
        System.out.println("Software Engineer");
        System.out.println("Database Administrator");
        System.out.println("Programmer");
    }

    @Override
    public void tahun(int tahun) {
        this.thn = tahun;
    }
}

public class abstractInterface {

    public static void main(String[] args) {
        Del laguboti = new Del("Vokasi", 3);
        laguboti.tahun(2021);
        System.out.println("Fakultas " + laguboti.nama_fakul);
        System.out.println("Berdiri tahun " + laguboti.thn);
        laguboti.jenisprodi();
        System.out.println("Keahlian dari mahasiswa " + "fakultas " + laguboti.nama_fakul);
        laguboti.skill();
    }
}
