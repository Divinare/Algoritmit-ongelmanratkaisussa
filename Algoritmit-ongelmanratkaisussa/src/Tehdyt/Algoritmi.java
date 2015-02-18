/**
 * 
 * V1 : 3*3 + 2*2 = 14p
 * V2 : 3*3 + 2*2 = 14p
 * V3 : 5*3
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Joe
 * Course: Algorithms in problem solving
 */

public class Algoritmi {

    public static void main(String[] args) {
        IO io = new IO();

        long a = io.nextInt();
        if (a < 1000000) {
            while (a != 1) {
                io.print(a + " ");
                if (a % 2 == 0) {
                    a = a / 2;
                } else {
                    a *= 3;
                    a++;
                }
                if (a == 1) {
                    io.print(a);
                }
            }
        }
        io.close();
    }
}
