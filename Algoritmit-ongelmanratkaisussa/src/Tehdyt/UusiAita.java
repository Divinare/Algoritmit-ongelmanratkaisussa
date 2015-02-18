/**
 * @author Joe Course: Algorithms in problem solving
 */
public class UusiAita {

    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();

        int height = 200;
        int mod = 1000000007;
        int d[][] = new int[height][n];

        for (int y = 0; y < d.length; y++) {
            d[y][0] = 1;
        }
        for (int i = 1; i < n; i++) {

            int a = io.nextInt();

            if (a == 0) {
                for (int y = 0; y < d.length; y++) {
                    if (y - 1 >= 0 && i - 1 >= 0) {
                        d[y][i] += d[y - 1][i - 1];
                        d[y][i] %= mod;
                    }
                    if (y + 1 < height && i - 1 >= 0) {
                        d[y][i] += d[y + 1][i - 1];
                        d[y][i] %= mod;
                    }
                }
            } else {
                if (i == 0) {
                    d[a][i] = 1;
                } else {
                    if (a - 1 >= 0 && i - 1 >= 0) {
                        d[a][i] += d[a - 1][i - 1];
                        d[a][i] %= mod;
                    }
                    if (a + 1 < height && i - 1 >= 0) {
                        d[a][i] += d[a + 1][i - 1];
                        d[a][i] %= mod;
                    }
                }
            }
        }

        int result = 0;
        for (int y = 0; y < 200; y++) {
            result+=d[y][n-1];
            result %= mod;
        }
        io.println(result);

        io.close();

    }
}
