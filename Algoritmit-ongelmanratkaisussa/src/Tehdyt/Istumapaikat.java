
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Istumapaikat {

    private static void readInputs(IO io, int[][] neighbours, long[][] capacities, int connections, ArrayList<Character> letters) {

        int endNode = neighbours.length - 1;
        for (int i = 0; i < connections; i++) {
            int a = io.nextInt();
            int b = io.nextInt();

            if (letters.get(a).equals(letters.get(b))) {
                continue; // not allowing to form path from the same letter to the same letter (its not a match)
            }

            if (letters.get(a) == 'T') {
                setInputToGraph(neighbours, capacities, a, b, endNode);
            } else {
                setInputToGraph(neighbours, capacities, b, a, endNode);
            }
        }
    }

    public static void setInputToGraph(int[][] neighbours, long[][] capacities, int a, int b, int endNode) {
        neighbours[a][b] = b;
        neighbours[0][a] = a;
        neighbours[b][endNode] = endNode;
        capacities[a][b] = 1;
        capacities[0][a] = 2;
        capacities[b][endNode] = 2;

        neighbours[b][a] = a;
        neighbours[0][b] = 0;
        neighbours[a][endNode] = endNode;
        capacities[b][a] = 0;
        capacities[0][b] = 0;
        capacities[a][endNode] = 0;
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

    public static void printTable(long[][] table, IO io) {
        io.println();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                io.print(table[i][j] + " ");
            }
            io.println();
        }
    }

    public static void printTableInt(int[][] table, IO io) {
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

        int netSize = nodes + 2;
        int[][] neighbours = new int[netSize][netSize];
        long[][] capacities = new long[netSize][netSize];
        long[][] flows = new long[netSize][netSize];
        ArrayList<Character> letters = new ArrayList();

        letters.add('f'); // just fill something to the start to get real letters starting index 1
        for (int i = 0; i < nodes; i++) {
            String c = io.next();
            letters.add(c.charAt(0));
        }

        readInputs(io, neighbours, capacities, connections, letters);

        //printTableInt(neighbours, io);
        int parents[] = edmondsKarp(neighbours, flows, capacities, 0, nodes + 1);

        //printTable(capacities, io);
        //printTable(flows, io);
        long sum = 0;
        for (long x : flows[0]) {
            sum += x;
        }

        if (sum == nodes) {
            int tablesGiven = 0;
            int[] visited = new int[nodes + 1];
            Queue<Integer>[] seats = new ArrayDeque[nodes];

            for (int i = 1; i <= nodes; i++) {

                if (flows[0][i] == 2 && visited[i] != -1) {
                    seats[tablesGiven] = new ArrayDeque();
                    visited[i] = -1;

                    int start = neighbours[0][i];
                    visited[start] = -1;
                    seats[tablesGiven].add(start);
                    int current = findChild(flows, start, nodes, visited);

                    while (current != start && current != 0) {
                        seats[tablesGiven].add(current);
                        current = findChild(flows, current, nodes, visited);
                    }

                    tablesGiven++;
                }

            }
            io.println(tablesGiven);

            for (int table = 0; table < tablesGiven; table++) {
                io.print(seats[table].size() + " ");
                while (!seats[table].isEmpty()) {
                    io.print(seats[table].poll() + " ");
                }
                io.println("");
            }

        } else {
            io.println("QAQ");
        }

        io.close();
    }

    public static int findChild(long[][] flows, int start, int nodes, int[] visited) {
        int child = 0;
        for (int j = 1; j <= nodes; j++) {

            if (flows[start][j] != 0) {

                if (visited[j] != -1) {
                    child = j;
                    visited[j] = -1;
                    return child;
                }
            }
        }
        return child;
    }

}
