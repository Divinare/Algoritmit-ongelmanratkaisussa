
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Seuranhaku {

    private static void readInputs(IO io, ArrayList<Integer>[] sideList, long[][] flows, long[][] capacities, int pairs, ArrayList<Character> letters) {

        // add 0 and n to the sideList
        ArrayList<Integer> startNodeList = new ArrayList();
        ArrayList<Integer> endNodeList = new ArrayList();
        sideList[0] = startNodeList;
        int endNode = sideList.length - 1;
        sideList[endNode] = endNodeList;
        int[] addedNodes = new int[endNode];
        for (int i = 0; i < pairs; i++) {

            int a = io.nextInt();
            int b = io.nextInt();

            if (letters.get(a).equals(letters.get(b))) {
                continue; // not allowing to form path from the same letter to the same letter (its not a match)
            }
            if (sideList[a] == null) {
                ArrayList<Integer> list = new ArrayList();
                sideList[a] = list;
            }
            if (sideList[b] == null) {
                ArrayList<Integer> list = new ArrayList();
                sideList[b] = list;
            }
            flows[a][b] = 0;
            capacities[a][b] = 1;
            sideList[a].add(b);
            sideList[b].add(a);
            if (addedNodes[a] != 1) {
                if (letters.get(a) == 'E') {
                    sideList[0].add(a);
                    capacities[0][a] = 1;
                } else {
                    sideList[a].add(endNode);
                    capacities[a][endNode] = 1;
                }
                addedNodes[a] = 1;
            }
            if (addedNodes[b] != 1) {
                if (letters.get(b) == 'E') {
                    sideList[0].add(b);
                    capacities[0][b] = 1;
                } else {
                    sideList[b].add(endNode);
                    capacities[b][endNode] = 1;
                }
                addedNodes[b] = 1;
            }

        }
    }

    private static boolean fordFulkersonAlgorithm(ArrayList<Integer>[] sideList, long[][] flows, long[][] capacities) {

        boolean foundPath = false;
        ArrayDeque<Integer> path = new ArrayDeque();
        Queue<Integer> queue = new ArrayDeque();

        int lastNode = sideList.length - 1;
        int[] previous = new int[lastNode + 1];
        int[] visited = new int[lastNode + 1];

        for (Integer neighbour : sideList[0]) {
            if (capacities[0][neighbour] > flows[0][neighbour]) {
                queue.add(neighbour);
                previous[neighbour] = 0;
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

        if (foundPath) {

            int current = previous[lastNode];
            //long smallestFlow = capacities[current][lastNode] - flows[current][lastNode];
            path.add(lastNode);
            while (true) {
                path.add(current);

                if (current == 0) {
                    break;
                }
                int temp = current;
                current = previous[current];
                long currentFlow = capacities[current][temp] - flows[current][temp];
                //    if (currentFlow < smallestFlow) {
                //         smallestFlow = currentFlow;
                //     }
            }
            //  System.out.println("smallestFlow: " + smallestFlow);
         /*
             System.out.println("path: ");
             for(int s : path) {
             System.out.print(s + " ");
             }
             System.out.println("");
             */
            increaseFlow(path, flows, 1);

        }
        return foundPath;
    }

    private static void increaseFlow(ArrayDeque<Integer> path, long[][] flows, long increaseAmount) {
        int previous = path.poll();

        while (!path.isEmpty()) {
            int current = path.poll();
            flows[current][previous] += increaseAmount;
            flows[previous][current] -= increaseAmount;
            previous = current;
        }
    }

    public static void main(String[] args) {

        IO io = new IO();

        int users = io.nextInt();
        int pairs = io.nextInt();

        int sideListSize = users + 2;
        ArrayList<Integer>[] sideList = new ArrayList[sideListSize];
        long[][] flows = new long[sideListSize][sideListSize];
        long[][] capacities = new long[sideListSize][sideListSize];
        ArrayList<Character> letters = new ArrayList();

        letters.add('f'); // just fill something to the start, its not needed
        for (int i = 0; i < users; i++) {
            String c = io.next();
            letters.add(c.charAt(0));
        }
        //  for (int i = 0; i < letters.size(); i++) {
        //          io.print(letters.get(i) + " ");
        //      }

        readInputs(io, sideList, flows, capacities, pairs, letters);

        while (true) {
            boolean foundPath = fordFulkersonAlgorithm(sideList, flows, capacities);
            if (!foundPath) {
                break;
            }
        }

        long result = 0;
        // System.out.println("sl " + sideList[1].size());
        for (Integer neighbour : sideList[0]) {
            result += flows[0][neighbour];
        }
        io.println(result);

        for (int start : sideList[0]) {
            for (int end : sideList[start]) {
                if (flows[start][end] > 0) {
                    io.println(start + " " + end);
                }
            }

        }
/*
        for (int i = 0; i < sideList.length; i++) {
            ArrayList<Integer> sl = sideList[i];
            if (sl != null) {
                System.out.print(i + ": ");

                for (int j = 0; j < sl.size(); j++) {
                    System.out.print("n:" + sl.get(j) + " w:" + flows[i][sl.get(j)] + " " + " cw: " + flows[sl.get(j)][i] + " ");
                }
                System.out.println("");
            }
        }
*/
        io.close();

    }
}
