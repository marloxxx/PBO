import java.util.Vector;

public class AddElementstoVector {
    public static void main(String[] args) {
        Vector v1 = new Vector();
        v1.add(1);
        v1.add(2);
        v1.add("tipe string");
        v1.add(92.12f);
        System.out.println("Vector v1: " + v1);
        Vector v2 = new Vector();
        v2.add(1);
        v2.add(2);
        v2.add(3);
        System.out.println("Vector v2: " + v2);
    }
}
