
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Nakkikioski {

     public static void main(String[] args) {


        IO io = new IO();

        int events = io.nextInt();

        ArrayList<Integer> queue = new ArrayList<Integer>();
        while (events > 0) {

            int type = io.nextInt();

            // queue.put(x);
            if (type == 1) {
                int x = io.nextInt();
                queue.add(x);
            }
            // i. guy's id
            if (type == 2) {
                int i = io.nextInt();
                io.println(queue.get(i-1));

            }
            // poll first at queue
            if (type == 3) {
                queue.remove(0);
            }
            // i. guy gets bored and is removed from queue
            if (type == 4) {
                int i = io.nextInt();
                queue.remove(i-1);
            }
            events--;
        }

        io.close();

    }
}
