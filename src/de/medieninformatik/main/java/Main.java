import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The <code>Main</code> class is used to ...
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Main {

    /**
     * Starting point of the application. This method is used to ...
     */
    public static void main (String[] args) {
        // ...
        LinkedList<String> lstr = new LinkedList<>();
        lstr.add("Hallo");
        lstr.add("du");
        lstr.add("da");

        lstr.subList(0, 2);
        Iterator itr = lstr.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        Iterator itr2 = lstr.descendingIterator();
        while (itr2.hasNext()) {
            System.out.println(itr2.next());
        }

        ArrayList<String> arr = new ArrayList<>(4);
        arr.add("!");

        lstr.toArray(arr.toArray());

        System.out.println(lstr);

        ArrayList<String> arrs = new ArrayList<>();
        arrs.add("Heyo");
        ArrayList<String> arrs2 = arrs;

        arrs2.add("du");
        arrs.add("da!");

        System.out.println(arrs);
        System.out.println(arrs2);

        int size = 7;
        System.out.println(size >> 1);
        System.out.println(size << 1);
        size = 16;
        System.out.println(size >> 1);
        System.out.println(size << 1);

        lstr.subList(1,3);

    }
}
