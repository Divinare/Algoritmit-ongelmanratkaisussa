import java.util.Arrays;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Kilpailu {

    public static int sums[];
    public static int index = 0;
    
    static void T(int sum, int j, int[] a) {
        sums[sum]++;
        index++;
        for(int i = j; i < a.length; i++) {
           T((sum+a[i]), (i+1), a);
        }
    }
    
    
    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();
        int a[] = new int[n]; //a = kaikki tehtävien pisteet
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = io.nextInt();
            sum += a[i];
        }
        Kilpailu.sums = new int[sum+1];
        for(int j = 0; j < n; j++) {
            T(a[j], (j+1), a);
        }
         
        sums[0] = 1;
        for(int i = 0; i < sums.length; i++) {
            io.println(sums[i]);        }
        io.close();

    }
}