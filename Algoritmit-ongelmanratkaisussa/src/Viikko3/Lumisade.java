/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Lumisade {

    public static void main(String[] args) {

        IO io = new IO();
        int houses = io.nextInt();
        int events = io.nextInt();
        int n = houses;
        // make n a power of 2
        while ((n & (n - 1)) != 0) {
            n++;
        }
        //  io.print("n is " + n);
        long sTree[] = new long[n * 2];

        // forming segmentTree:
        for (int i = 0; i < n; i++) {
            sTree[i] = 0;
        }

        while (events > 0) {
            int type = io.nextInt();

            // a..b snows
            if (type == 1) {
                int a = io.nextInt();
                int b = io.nextInt();
                int s = io.nextInt();
                a--;
                b--;

                int k = a + n;
                sTree[k] += s;
                for (k /= 2; k >= 1; k /= 2) {
                    sTree[k] = sTree[(2 * k)] + sTree[(2 * k + 1)];
                }
                k = (b + 1 + n);
                sTree[k] -= s;
                for (k /= 2; k >= 1; k /= 2) {
                    sTree[k] = sTree[(2 * k)] + sTree[(2 * k + 1)];
                }
            }
            //snow at house x
            if (type == 2) {
                int a = n;
                int b = n+io.nextInt();
                a--;
                b--;
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

                io.println(sum);
            }

            events--;
        }
        io.close();

    }
}
