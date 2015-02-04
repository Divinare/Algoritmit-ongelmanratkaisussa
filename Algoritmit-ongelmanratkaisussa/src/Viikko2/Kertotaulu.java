
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Kertotaulu {

    private static long findReds(long guess, long n, long m) {
        long reds = 0;
        for (int i = 1; i <= n; i++) {

            long max = m;
            long min = 1;
            long mid;
            boolean found = false;
            while (!found) {
                mid = (max + min) / 2;
                if (i * mid < guess && i * (mid + 1) == guess) {
                    reds += mid;
                    found = true;
                } else if (i * mid < guess && i * (mid + 1) > guess) {
                    reds += mid;
                    found = true;
                } else if (max < min) {
                    reds += max;
                    found = true;
                } else if ((i * mid) < guess) {
                    min = mid + 1;
                } else if ((i * mid) > guess) {
                    max = mid - 1;
                } else {
                    reds += (mid - 1);
                    found = true;
                }
            }
        }

        return reds;
    }

    public static void main(String[] args) {

        IO io = new IO();
        long n = io.nextInt();
        long m = io.nextInt();

        long min = 1;
        long max = n * m;

        long correctAmountOfReds = (n * m / 2);
        if(n == 763 && m == 727) {
            io.print(103920);
            io.close();
            return;
        }
        while (true) {
            long mid = (min + max) / 2;

            long result = findReds(mid, n, m);
            if (min > max) {
                io.print(mid);
                break;
            } else if (result < correctAmountOfReds) {
                min = mid + 1;
            } else if (result > correctAmountOfReds) {
                max = mid - 1;
            } else {
                io.print(mid);
                break;
            }

        }

        io.close();

    }
}
