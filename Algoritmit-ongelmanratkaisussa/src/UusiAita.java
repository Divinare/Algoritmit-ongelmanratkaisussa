
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class UusiAita {

    public static boolean checkIfValid(int aIndex, int bIndex, int aHeight, int bHeight) {
        if (aHeight <= 0 || bHeight <= 0) {
            return false;
        }

        // if heighs abs is > index abs
        if (Math.abs(aHeight - bHeight) > Math.abs(aIndex - bIndex)) {
            return false;
        }
        if (aHeight % 2 == bHeight % 2) {
            if (Math.abs(aIndex - bIndex) % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    public static int countPossibilities(int startIndex, int endIndex, int startHeight, int endHeight) {
        if (!checkIfValid(startIndex, endIndex, startHeight, endHeight)) {
            return 0;
        }
        if (startIndex == endIndex - 1) {
            return 1;
        }

        int total = 0;
        total += countPossibilities(startIndex + 1, endIndex, startHeight + 1, endHeight);
        total += countPossibilities(startIndex + 1, endIndex, startHeight - 1, endHeight);
        return total;
    }

    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();
        /*  
         List<Map.Entry<Integer,Integer>> wire = new ArrayList();
         for(int i = 1; i <= n; i++) {
         int x = io.nextInt();
         Entry e = new Entry(i, x);        
         wire.add(e);
         }
         */

        //aIndex bIndex aHeight bHeight
     //   io.print(countPossibilities(0, 1, 1, 2));

        int d[][] = new int[n + 1][27];
        for (int j = 1; j <= 26; j++) {
            d[1][j] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 26; j++) {
                if (j > 1) {
                    d[i][j] += d[i - 1][j - 1];
                }
                if (j < 26) {
                    d[i][j] += d[i - 1][j + 1];
                }
            }
        }

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < 26; j++) {
                io.print(d[i][j] + " ");
            }
            io.println("");
        }
        int t = 0;
        for (int j = 1; j <= 26; j++) {
            t += d[n][j];
        }
        io.println("t: " + t);

        io.close();

    }
}
