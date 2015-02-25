
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Tietullit {

    private static class Node implements Comparable<Node> {

        public int location;
        public int cost;
        public int distance;

        public Node(int location, int cost) {
            this.location = location;
            this.cost = cost;
            this.distance = 9999999;
        }

        @Override
        public int compareTo(Node o) {
            if (distance > o.distance) {
                return 1;
            }
            if (distance < o.distance) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {

        IO io = new IO();

        int cities = io.nextInt();
        int roads = io.nextInt();
        ArrayList<Node>[] sideList = new ArrayList[cities + 1];

        PriorityQueue<Node> q = new PriorityQueue<Node>(cities);

        for (int i = 0; i < roads; i++) {

            int a = io.nextInt();
            int b = io.nextInt();
            int cost = io.nextInt();

            if (sideList[a] == null) {
                ArrayList<Node> newNode = new ArrayList();
                sideList[a] = newNode;
            }
            Node n1 = new Node(b, cost);
            sideList[a].add(n1);

            if (sideList[b] == null) {
                ArrayList<Node> newNode = new ArrayList();
                sideList[b] = newNode;
            }
            Node n2 = new Node(a, cost);
            sideList[b].add(n2);
        }

        Node start = new Node(1, 0);
        start.distance = 0;
        q.add(start);

        Node result = new Node(0, 0);
        while (!q.isEmpty()) {
            Node current = q.poll();
            if (current.location == cities) {
                result = current;
                break;
            }
            ArrayList<Node> neighbours = sideList[current.location];
            for (Node neighbour : neighbours) {
                Integer newDistance = current.distance + neighbour.cost;
                Integer oldDistance = neighbour.distance;

                if (newDistance < oldDistance) {
                    neighbour.distance = newDistance;
                    q.offer(neighbour);
                }

            }
        }

        io.println(result.distance);
        io.close();

    }
}
