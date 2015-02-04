
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Soittolista {

    public static long findCorrectLength(int n, int a, int b, long[] sTree) {

        long sum = 0;
        while (a <= b) {
            if (a % 2 == 1) {
                sum += sTree[a++];
            }
            if (b % 2 == 0) {
                sum += sTree[b--];
            }
            a /= 2;
            b /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {

        IO io = new IO();

        int songs = io.nextInt();

        int n = songs;
        // make n a power of 2
        while ((n & (n - 1)) != 0) {
            n++;
        }
        //  io.print("n is " + n);
        long sTree[] = new long[n * 2];
        for (int i = n; i < n + songs; i++) {
            sTree[i] = io.nextLong();
        }

        for (int i = 1; i < n; i++) {
            int k = i;
            k += n;
            for (k /= 2; k >= 1; k /= 2) {
                sTree[k] = sTree[(2 * k)] + sTree[(2 * k + 1)];
            }
        }

        int events = io.nextInt();
        int uoleviGoesAngry = 0;


        while (events > 0) {
            int type = io.nextInt();

            if (type == 1) {
                int a = n + io.nextInt();
                int b = 2 * n;
                long x = io.nextInt();

                a--;
                b--;

                if (sTree[a] != x) {

                    while (a <= b) {
                        int c = (a + b) / 2;
                        long sum;
                        sum = findCorrectLength(n, a, c, sTree);
                        if (sum == x) {
                            break;
                        }
                        if (sum > x) {
                            b = c - 1;
                        }
                        if (sum < x) {
                            x -= sum;
                            a = c + 1;
                        }
                        if (a > b) {
                            uoleviGoesAngry++;
                        }
                    }
                }
            }

            if (type == 2) {

                int k = n + io.nextInt();
                int x = io.nextInt();

                k--;
                sTree[k] = x;
                for (k /= 2; k >= 1; k /= 2) {
                    sTree[k] = sTree[(2 * k)] + sTree[(2 * k + 1)];
                }
            }

            events--;
        }
        io.println(uoleviGoesAngry);
        io.close();

    }
}
