import java.util.*;

public class ContohHashTable {
    public static void main(String args[]) {
        Hashtable<Integer, String> ht1 = new Hashtable<Integer, String>();
        Hashtable<Integer, String> ht2 = new Hashtable<Integer, String>();
        ht1.put(1, "pulpen");
        ht1.put(2, "buku");
        ht1.put(3, "kertas");
        ht2.put(4, "monitor");
        ht2.put(5, "mouse");
        ht2.put(6, "keyboad");
        System.out.println("Pemetaan dari HashTable1"+ht1);
        System.out.println("Pemetaan dari HashTable2"+ht2);
    }
}
