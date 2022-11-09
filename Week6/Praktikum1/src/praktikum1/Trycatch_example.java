package praktikum1;

public class Trycatch_example {

    public static void main(String[] args) {
        String[] str = new String[2];
        try {
            str[0] = "Hai";
            str[1] = "Kamu";
            str[2] = "Cantik";
            System.out.println("String 1 : " + str[0]);
            System.out.println("String 2 : " + str[1]);
            System.out.println("String 3 : " + str[2]);
        } catch (Exception e) {
            System.out.println("Pesan Error : " + e.getMessage());
        }
    }
}
