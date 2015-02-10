
import java.util.Arrays;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Pitkospuut {

    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt(); // lautojen lukumäärä
        int m = io.nextInt(); // reitin pituus

        int a[] = new int[n]; // a = laudat
        for (int i = 0; i < n; i++) {
            a[i] = io.nextInt();
        }
        Arrays.sort(a);
        int d[] = new int[m + 1]; // d = ratkaisut pienempiin osaongelmiin
        int e[] = new int[m + 1];
        d[0] = 0;
        for (int i = 1; i <= m; i++) {
            d[i] = m + 1;
            for (int j = 0; j < n; j++) {
                if (i - a[j] < 0) {
                    continue;
                }

                if (d[i - a[j]] + 1 < d[i]) {
                    d[i] = d[i - a[j]] + 1;
                    e[i] = a[j];
                }

            }
        }

        while (m > 0) {
            io.println(e[m]);
            m -= e[m];
        }
        io.close();

    }
}
