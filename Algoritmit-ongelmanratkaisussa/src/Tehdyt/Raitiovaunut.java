
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Raitiovaunut {

    public static int goThroughSidelist(Map<Integer, Set<Integer>> sideList, int visitedNode) {
        int result = 1;
        Set<Integer> neighbours = sideList.get(visitedNode);
        if(neighbours == null) {
            return 0;
        }
        sideList.remove(visitedNode);
        for (Integer node : neighbours) {
                result += goThroughSidelist(sideList, node);
        }
        return result;
    }

    public static void main(String[] args) {

        IO io = new IO();

        int stops = io.nextInt();
        int connections = io.nextInt();
        Map<Integer, Set<Integer>> sideList = new HashMap();

        for (int i = 0; i < connections; i++) {

            int node1 = io.nextInt();
            int node2 = io.nextInt();
            if (!sideList.containsKey(node1)) {
                Set<Integer> newNode = new HashSet();
                newNode.add(node2);
                sideList.put(node1, newNode);
            } else {
                sideList.get(node1).add(node2);
            }
            if (!sideList.containsKey(node2)) {
                Set<Integer> newNode = new HashSet();
                newNode.add(node1);
                sideList.put(node2, newNode);
            } else {
                sideList.get(node2).add(node1);
            }
        }
        int record = 0;

        while (!sideList.isEmpty()) {
            int current = sideList.keySet().iterator().next();
            int result = 0;
            if(sideList.size() <= record){
                break;
            }
            result = goThroughSidelist(sideList, current);
            if (result > record) {
                record = result;
            }
        }
        io.print(record);
        io.close();

    }
}
