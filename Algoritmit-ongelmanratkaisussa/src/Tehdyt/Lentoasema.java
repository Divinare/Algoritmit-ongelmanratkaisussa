
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Lentoasema {

    private static void readInputs(IO io, int n, int[][] neighbours, long[][] capacities, int connections, ArrayList<Integer> nodeCapacities) {
        for (int i = 0; i < connections; i++) {
            int a = io.nextInt();
            int b = io.nextInt();
            long capacity = nodeCapacities.get(a);

            if (capacity == -1) {
                capacity = Long.MAX_VALUE;
            }
            neighbours[a][a + n] = a + n;
            neighbours[a + n][b] = b;
            capacities[a][a + n] = capacity;
            capacities[a + n][b] = Long.MAX_VALUE;

            neighbours[a + n][a] = a;
            neighbours[b][a + n] = a + n;
            capacities[a + n][a] = 0;
            capacities[b][a + n] = 0;
        }
    }

    public static int[] edmondsKarp(int[][] neighbours, long[][] flows, long[][] capacities, int start, int end) {
        int n = capacities.length;
        while (true) {
            int[] parents = new int[n];
            Arrays.fill(parents, -1);
            parents[start] = start;
            long[] capacityToNode = new long[n]; // Capacity of path to node
            capacityToNode[start] = Integer.MAX_VALUE;

            bfs(capacityToNode, parents, neighbours, flows, capacities, start, end);

            // Path not found anymore, end the loop
            if (parents[end] == -1) {
                return parents;
            }
        }
    }

    public static void bfs(long[] capacityToNode, int[] parents, int[][] neighbours, long[][] flows, long[][] capacities, int start, int end) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbour : neighbours[current]) {
                // Capacity available, neighbour not visited before
                if (capacities[current][neighbour] - flows[current][neighbour] > 0 && parents[neighbour] == -1) {
                    parents[neighbour] = current;
                    capacityToNode[neighbour] = Math.min(capacityToNode[current], capacities[current][neighbour] - flows[current][neighbour]);
                    if (neighbour != end) {
                        queue.offer(neighbour);
                    } else {
                        // Write flows
                        while (parents[neighbour] != neighbour) {
                            current = parents[neighbour];
                            flows[current][neighbour] += capacityToNode[end];
                            flows[neighbour][current] -= capacityToNode[end];
                            neighbour = current;
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void printTable(int[][] table, IO io) {
        io.println();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                io.print(table[i][j] + " ");
            }
            io.println();
        }
    }

    public static void main(String[] args) {

        IO io = new IO();

        int nodes = io.nextInt();
        int connections = io.nextInt();

        int netSize = nodes * 2+1;
        int[][] neighbours = new int[netSize][netSize];
        long[][] capacities = new long[netSize][netSize];
        long[][] flows = new long[netSize][netSize];
        ArrayList<Integer> nodeCapacities = new ArrayList();

        nodeCapacities.add(1); // just fill something to the start to get real letters starting index 1
        for (int i = 0; i < nodes; i++) {
            nodeCapacities.add(io.nextInt());
        }

        readInputs(io, nodes, neighbours, capacities, connections, nodeCapacities);

        // printTable(neighbours, io);
        int parents[] = edmondsKarp(neighbours, flows, capacities, 1, nodes);

    //    printTable(capacities, io);
        //    printTable(flows, io);
        long sum = 0;
        for (long x : flows[1]) {
            sum += x;
        }
        io.println(sum);

        io.close();
    }
}
