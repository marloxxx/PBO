package praktikum2;

import java.util.Vector;
public class VectorExample {
    public static void main(String[] args) {
        int n = 5;
        Vector<Integer> v = new Vector<Integer>();
        for (int i = 0; i < n; i++) {
            v.add(i);
        }
        System.out.println(v);
        v.remove(3);
        System.out.println(v);
        for (int i = 0; i < n; i++) {
            System.out.println(v.get(i) + " ");
        }
    }
}
