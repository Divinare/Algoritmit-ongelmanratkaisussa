
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Hintavertailu {

    private static class Entry {

        public int min;
        public int count;

        public Entry(int key, int value) {
            this.min = key;
            this.count = value;
        }

    }

    public static void main(String[] args) {

        IO io = new IO();

        int shops = io.nextInt();

        int n = shops;
        // make n a power of 2
        while ((n & (n - 1)) != 0) {
            n++;
        }
        Entry sTree[] = new Entry[n * 2];

        // forming segmentTrees:
        for (int i = n; i < n + shops; i++) {
            if (sTree[i] == null) {
                sTree[i] = new Entry(0, 1);
            }
            sTree[i].min = io.nextInt();
        }

        for (int i = 1; i < n; i++) {
            int k = i;
            k += n;
            for (k /= 2; k >= 1; k /= 2) {
                Entry c1 = sTree[(2 * k)];
                Entry c2 = sTree[(2 * k + 1)];
                if (sTree[k] == null) {
                    sTree[k] = new Entry(0, 0);
                }
                if (c1 == null) {
                    c1 = new Entry(Integer.MAX_VALUE, 0);
                }
                if (c2 == null) {
                    c2 = new Entry(Integer.MAX_VALUE, 0);
                }
                if (c1.min == c2.min) {
                    sTree[k].min = c1.min;
                    sTree[k].count = c1.count + c2.count;
                } else if (c1.min < c2.min) {
                    sTree[k].min = c1.min;
                    sTree[k].count = c1.count;
                } else {
                    sTree[k].min = c2.min;
                    sTree[k].count = c2.count;
                }
            }
        }

        int events = io.nextInt();

        while (events > 0) {

            int type = io.nextInt();

            if (type == 1) {
                int k = io.nextInt();
                int x = io.nextInt();

                k += n;
                k--;
                
                sTree[k].min = x;
                for (k /= 2; k >= 1; k /= 2) {
                    Entry c1 = sTree[(2 * k)];
                    Entry c2 = sTree[(2 * k + 1)];
                    if (c1 == null) {
                        c1 = new Entry(Integer.MAX_VALUE, 0);
                    }
                    if (c2 == null) {
                        c2 = new Entry(Integer.MAX_VALUE, 0);
                    }
                    if (c1.min == c2.min) {
                        sTree[k].min = c1.min;
                        sTree[k].count = c1.count + c2.count;
                    } else if (c1.min < c2.min) {
                        sTree[k].min = c1.min;
                        sTree[k].count = c1.count;
                    } else {
                        sTree[k].min = c2.min;
                        sTree[k].count = c2.count;
                    }
                }
            }

            if (type == 2) {
                int a = io.nextInt();
                int b = io.nextInt();

                a += n;
                b += n;
                a--;
                b--;
                int count = 0;
                int min = Integer.MAX_VALUE;
                while (a <= b) {
                    if (a % 2 == 1) {
                        if (sTree[a].min == min) {
                            count += sTree[a].count;
                        }
                        if (sTree[a].min < min) {
                            min = sTree[a].min;
                            count = sTree[a].count;
                        }
                        a++;
                    }
                    if (b % 2 == 0) {
                        if (sTree[b].min == min) {
                            count += sTree[b].count;
                        }
                        if (sTree[b].min < min) {
                            min = sTree[b].min;
                            count = sTree[b].count;
                        }
                        b--;
                    }

                    a /= 2;
                    b /= 2;
                }
                io.println(min + " " + count);
            }

            events--;
        }

        io.close();

    }
}
