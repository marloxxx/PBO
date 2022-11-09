package praktikum1;

public class NewClassMotor {
    private String nama;
    private int tahun_beli;
    private String merk;

    public NewClassMotor(){

    }
    public NewClassMotor(String nama, int tahun_beli, String merk){
        this.nama = nama;
        this.tahun_beli = tahun_beli;
        this.merk = merk;
    }
    public void display(){
        String temp = String.format("motor saya %s, saya beli pada tahun %d, dengan merk %s", getNama(), getTahunbeli(), getMerk());
        System.out.println(temp);
    }
    public String getNama(){
        return this.nama;
    }
    public int getTahunbeli(){
        return this.tahun_beli;
    }
    public String getMerk(){
        return this.merk;
    }
}
