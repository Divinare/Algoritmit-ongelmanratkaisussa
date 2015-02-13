
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Bittijono {

    public static void main(String[] args) {

        IO io = new IO();

        int n = io.nextInt();

        if (n == 1) {
            io.println(2);
        } else if (n == 2) {
            io.println(4);
        }
        else {
                        
            long f1 = 6;
            long f2 = 4;
            for (int i = 0; i < (n-3); i++) {
                long tempF1 = f1;
                f1 = (long) ((f1+f2) % (Math.pow(10, 9)+7));
                f2 = tempF1;
            }
            io.println(f1);
        }
        

        io.close();
    }

}
