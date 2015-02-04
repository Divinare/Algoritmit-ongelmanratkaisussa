
/**
 * @author Joe Course: Algorithms in problem solving
 */

public class Mummola {

    public static double calc(double num) {
        return Math.pow(2.718281828459045, num) + Math.sqrt(num);
    }

    public static void main(String[] args) {

        IO io = new IO();

        double d = io.nextDouble();
        if (1 >= 1 && d <= 400) {

            double numToCompare = Math.pow(10, -6);

            double upper = 400;
            double lower = 0;
            double x = (upper + lower) / 2;
            int i = 0;
            while (true) {
                if (d - calc(x) > numToCompare) {
                    lower = x;
                } else if (d - calc(x) < -numToCompare) {
                    upper = x;
                } else {
                    io.println(x);
                    io.close();
                    return;
                }
               x = (upper + lower) / 2;
            }
        }

        io.close();

    }
}
