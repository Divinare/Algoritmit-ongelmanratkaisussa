
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Asennus {

    public static void main(String[] args) {

        IO io = new IO();

        int programs = io.nextInt();
        int dependences = io.nextInt();
        ArrayList<Integer>[] sideList = new ArrayList[programs + 1];

        int[] c = new int[programs + 1];
        Queue<Integer> u = new ArrayDeque<>();

        for (int i = 0; i < dependences; i++) {

            int node1 = io.nextInt();
            int node2 = io.nextInt();

            if (sideList[node1] == null) {
                ArrayList<Integer> newNode = new ArrayList();
                newNode.add(node2);
                sideList[node1] = newNode;
            } else {
                sideList[node1].add(node2);
            }
            c[node2]++;
        }
        for (int i = 1; i <= programs; i++) {
            if (c[i] == 0) {
                u.add(i);

            }
        }

        boolean directionalLoopFound = false;
        Queue<Integer> r = new ArrayDeque<>();

        for (int i = 0; i < programs; i++) {
            if(u.isEmpty()) {
                directionalLoopFound = true;
                break;
            }
            int current = u.poll();

            r.add(current);
            if (sideList[current] != null) {
                for (int j = 0; j < sideList[current].size(); j++) {
                    int s = sideList[current].get(j);
                    c[s]--;
                    if (c[s] == 0) {
                        u.add(s);
                    }
                }
            }
        }
        if (directionalLoopFound) {
            io.println("QAQ");
        } else {
            while(!r.isEmpty()) {
                io.print(r.poll() + " ");
            }
        }

        io.close();

    }
}
