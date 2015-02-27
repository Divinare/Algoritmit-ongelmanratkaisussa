
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Nettiyhteys {
    /*
     public static class Node {

     long weight;
     long capacity;
     boolean rightWay;

     public Node(long capacity, boolean rightWay) {
     this.weight = 0;
     this.capacity = capacity;
     this.rightWay = rightWay;
     }

     public boolean full() {
     if (rightWay) {
     return weight == capacity;
     }
     return weight == 0;
     }

     public long fits() {
     if (rightWay) {
     return capacity - weight;
     }
     return weight;
     }

     }*/

    private static void readInputs(IO io, ArrayList<Integer>[] sideList, Long[][] flows, Long[][] capacities, int connections) {

        for (int i = 0; i < connections; i++) {

            int a = io.nextInt();
            int b = io.nextInt();
            long capacity = io.nextLong();

            if (sideList[a] == null) {
                ArrayList<Integer> list = new ArrayList();
                sideList[a] = list;
            }
            if (sideList[b] == null) {
                ArrayList<Integer> list = new ArrayList();
                sideList[b] = list;
            }

            long n = capacity;
            capacities[a][b] = capacity;
            long flow = 0;
            flows[a][b] = flow;
            sideList[a].add(b);
         //   sideList[b].add(a);

        }
    }

    private static boolean fordFulkersonAlgorithm(ArrayList<Integer>[] sideList, Long[][] flows, Long[][] capacities) {

        boolean foundPath = false;
        ArrayDeque<Integer> path = new ArrayDeque();
        Queue<Integer> queue = new ArrayDeque();

        int lastNode = sideList.length - 1;
        int[] previous = new int[lastNode + 1];
        int[] visited = new int[lastNode + 1];

        for (Integer neighbour : sideList[1]) {
            if (capacities[1][neighbour] > flows[1][neighbour]) {
                queue.add(neighbour);
                previous[neighbour] = 1;
            }
        }
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            visited[current] = 1;
            if (current == lastNode) {
                foundPath = true;
                break;
            }
            ArrayList<Integer> neighbours = sideList[current];
            if (neighbours != null) {
                for (Integer neighbour : neighbours) {
                    if (capacities[current][neighbour] > flows[current][neighbour] && visited[neighbour] != 1) {
                        queue.add(neighbour);
                        previous[neighbour] = current;
                        visited[neighbour] = 1;
                    }
                }
            }

        }
        /*
         for (int i = 0; i < sideList.length; i++) {
         ArrayList<Integer> sl = sideList[i];
         if (sl != null) {
         System.out.print(i + ": ");

         for (int j = 0; j < sl.size(); j++) {
         System.out.print("n:" + sl.get(j) + " w:" + weights[i][sl.get(j)].weight + " " + " cw: " + weights[sl.get(j)][i].weight + " ");
         }
         System.out.println("");
         }
         }
         */
        if (foundPath) {

            int current = previous[lastNode];
            long smallestFlow = capacities[current][lastNode] - flows[current][lastNode];
            path.add(lastNode);
            while (true) {
                path.add(current);

                if (current == 1) {
                    break;
                }
                int temp = current;
                current = previous[current];
                long currentFlow = capacities[current][temp] - flows[current][temp];
                if (currentFlow < smallestFlow) {
                    smallestFlow = currentFlow;
                }
            }

            //  System.out.println("smallestWeight: " + smallestWeight);
            increaseFlow(path, flows, smallestFlow);

        }
        return foundPath;
    }

    private static void increaseFlow(ArrayDeque<Integer> path, Long[][] flows, long increaseAmount) {
        int previous = path.poll();

        while (!path.isEmpty()) {
            int current = path.poll();
           // flows[previous][current]+= increaseAmount;
            flows[current][previous] += increaseAmount;
            previous = current;
        }
    }

    public static void main(String[] args) {

        IO io = new IO();
        int machines = io.nextInt();
        int connections = io.nextInt();

        ArrayList<Integer>[] sideList = new ArrayList[machines + 1];
        Long[][] capacities = new Long[machines + 1][machines + 1];
        Long[][] flows = new Long[machines + 1][machines + 1];
        readInputs(io, sideList, flows, capacities, connections);

        while (true) {
            boolean foundPath = fordFulkersonAlgorithm(sideList, flows, capacities);
            //      System.out.println("found path " + foundPath);
            if (!foundPath) {
                break;
            }
        }
        long result = 0;
       // System.out.println("sl " + sideList[1].size());
        for (Integer neighbour : sideList[1]) {
            result += flows[1][neighbour];
        }
        io.println(result);
        io.close();

    }
}
