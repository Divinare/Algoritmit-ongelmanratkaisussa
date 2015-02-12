
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Bittijono {
    
    public static int beatifulLines = 0;
    
    public static void countUglyBitlines(int last, int secondLast, int loopCount, int n) {
        if (n == loopCount) {
            beatifulLines++;
            return;
        }
        loopCount++;
        //System.out.println("LoopCount: " + loopCount + "ugl: " + beatifulLines);

        //System.out.println("Last: " + last + " secondLast: " + secondLast);
        if (last == secondLast) {
            if (last == 1) {
                countUglyBitlines(0, last, loopCount, n);
            } else {
                countUglyBitlines(1, last, loopCount, n);
            }
        } else {
            countUglyBitlines(0, last, loopCount, n);
            countUglyBitlines(1, last, loopCount, n);
        }
    }
    
    public static void main(String[] args) {
        
        IO io = new IO();
        int n = io.nextInt();
        
        countUglyBitlines(0, 0, 2, n);
        countUglyBitlines(0, 1, 2, n);
        countUglyBitlines(1, 0, 2, n);
        countUglyBitlines(1, 1, 2, n);

        io.println(beatifulLines);
    }
    
}
