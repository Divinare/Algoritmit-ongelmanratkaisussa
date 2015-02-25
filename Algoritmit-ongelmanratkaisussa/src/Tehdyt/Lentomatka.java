
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Lentomatka {

    private static class Node implements Comparable<Node> {

        public int location;
        public long cost;
        public long price;
        public long discountPrice;

        public Node(int location, long cost) {
            this.location = location;
            this.cost = cost;
            this.price = Long.MAX_VALUE;
            this.discountPrice = Long.MAX_VALUE;
        }

        @Override
        public int compareTo(Node o) {
            if (price > o.price) {
                return 1;
            }
            if (price < o.price) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {

        IO io = new IO();

        int cities = io.nextInt();
        int connections = io.nextInt();
        ArrayList<Node>[] sideList = new ArrayList[cities + 1];

        PriorityQueue<Node> q = new PriorityQueue<Node>(cities);

        for (int i = 0; i < connections; i++) {

            int a = io.nextInt();
            int b = io.nextInt();
            long cost = io.nextLong();

            if (sideList[a] == null) {
                ArrayList<Node> newNode = new ArrayList();
                sideList[a] = newNode;
            }
            Node n1 = new Node(b, cost);
            sideList[a].add(n1);
        }

        Node start = new Node(1, 0);
        start.price = 0;
        start.discountPrice = 0;
        q.add(start);

        Node result = new Node(0, 0);
        while (!q.isEmpty()) {
            Node current = q.poll();
            if (current.location == cities) {
                result = current;
                break;
            }
            ArrayList<Node> neighbours = sideList[current.location];
            if(neighbours == null) continue;
            for (Node neighbour : neighbours) {
                long newPriceWithDiscount = Math.min(current.price + neighbour.cost / 2, current.discountPrice + neighbour.cost);
                long newPrice = current.price + neighbour.cost;
                long oldPrice = neighbour.price;
                long oldPriceWithDiscount = neighbour.discountPrice;

                if (newPriceWithDiscount < oldPriceWithDiscount) {
                    neighbour.discountPrice = newPriceWithDiscount;
                }
                if (newPrice < oldPrice) {
                    neighbour.price = newPrice;
                    q.offer(neighbour);
                }
            }
        }

        io.println(result.discountPrice);
        io.close();

    }
}
