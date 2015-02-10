
import java.util.HashMap;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Lukujono {

    public static HashMap<Integer, Integer> results = new HashMap();

    public static int calc(int n) {
        int sum = 0;
        if (n == 1) {
            return 1;
        }
        for (int i = n; i >= 2; i--) {
            if (results.containsKey(n / 1)) {
                sum += results.get(n / i);
            } else {
                int r = calc(n / i);
                sum += r;
                results.put(n / i, r);
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        IO io = new IO();

        int n = io.nextInt();

        int result = calc(n);
        io.println(result);

        io.close();

    }
}
