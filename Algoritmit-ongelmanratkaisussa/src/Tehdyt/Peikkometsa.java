
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Peikkometsa {

    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();
        int m = io.nextInt();

        int[][] goblins = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = io.next();

            for (int j = 1; j <= line.length(); j++) {
                if (line.charAt(j - 1) == 'P') {
                    goblins[i][j] = 1;
                } else {
                    goblins[i][j] = 0;
                }
            }
        }
        int[][] sum = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            int row = 0;
            for (int j = 1; j <= m; j++) {
                row += goblins[i][j];
                sum[i][j] = row;
                if (i > 0) {
                    sum[i][j] += sum[i - 1][j];
                }

              
            }
        }

        int q = io.nextInt();
        int safePlaces = 0;

        for (int i = 0; i < q; i++) {
            int y1 = io.nextInt();
            int x1 = io.nextInt();
            int y2 = io.nextInt();
            int x2 = io.nextInt();
            y1--;
            x1--;
            int total = sum[y2][x2] - sum[y1][x2] - sum[y2][x1] + sum[y1][x1];

            if (total == 0) {
                safePlaces++;
            }
        }

        io.print(safePlaces);
        io.close();

    }
}
