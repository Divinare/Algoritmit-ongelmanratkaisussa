/**
 * @author Joe
 * Course: Algorithms in problem solving
 */

public class Test {
    
     public static double calc(double num) {
         return Math.pow(2.718281828459045, num) + Math.sqrt(num);
    }
    
    public static void main(String[] args) {

        IO io = new IO();

        io.print(5/2 + "\n");
       // io.print(calc(1));
        
        io.close();

    }
}
