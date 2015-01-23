
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        //    sum[0][0] = goblins[0][0];

        for (int i = 1; i <= n; i++) {
            int row = 0;
            for (int j = 1; j <= m; j++) {
                row += goblins[i][j];
                sum[i][j] = row;
                if (i > 0) {
                    sum[i][j] += sum[i - 1][j];
                }

                /*
                 if (i > 0) {
                 sum[i][j] = sum[i - 1][j];
                 }
                 if (j > 0) {
                 sum[i][j] += sum[i][j - 1];
                 }
                 if (i > 0 && j > 0) {
                 sum[i][j] -= sum[i - 1][j - 1];
                 }
                 

                 //  sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
                 row += goblins[i][j];
                 sum[i][j] = row;
                 if (i > 0) {
                 sum[i][j] += sum[i - i][j];

                 }
                 if (j > 0) {
                 sum[i][j] += sum[i][j - 1];
                 }
                 if (i > 0 && j > 0) {
                 sum[i][j] += sum[i - 1][j - 1];
                 }
                 */
            }
        }
        //  io.print("SUM LENGTH: " + sum[0].length);
    /*
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                io.print(sum[i][j] + " ");
            }
            io.print("\n");
        }
*/
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
            /* 
             if (a > 0) {
             total -= sum[a - 1][d];
             }
             if (b > 0) {
             total -= sum[c][b - 1];
             }
             if (a > 0 && b > 0) {
             total += sum[a - 1][b - 1];
             }
             */
          //  io.println("total: " + total);
            if (total == 0) {
                safePlaces++;
            }
        }

        io.print(safePlaces);
        io.close();

    }
}
