
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Lampotila {

    public static void main(String[] args) {

        IO io = new IO();

        int days = io.nextInt();
        int n = days;
        // make n a power of 2
        while ((n & (n - 1)) != 0) {
            n++;
        }
        int minTree[] = new int[2 * n];
        int maxTree[] = new int[2 * n];

        // forming segmentTrees:
        for (int i = n; i < n + days; i++) {
            int next = io.nextInt();
            minTree[i] = next;
            maxTree[i] = next;
        }

        for (int i = 1; i < n; i++) {

            int k = i;
            k += n;
            for (k /= 2; k >= 1; k /= 2) {
                minTree[k] = Math.min(minTree[(2*k)], minTree[(2*k+1)]);
                maxTree[k] = Math.max(maxTree[(2*k)], maxTree[(2*k+1)]);
            }
        }
        
        int questions = io.nextInt();
        while(questions > 0) {
            
            int qMin = io.nextInt();
            int qMax = io.nextInt();
            
            int a = qMin + n;
            int b = qMax + n;
            a--;
            b--;
          //  io.println("n: " + n + "a: " + a + "b: " + b);
            int minValue = minTree[b];
            int maxValue = maxTree[b];
            while(a <= b) {
                if(a%2 == 1) {
                    minValue = Math.min(minValue, minTree[a]);
                    maxValue = Math.max(maxValue, maxTree[a]);
                    a++;
                }
                if(b%2 == 0) {
                    minValue = Math.min(minValue, minTree[b]);
                    maxValue = Math.max(maxValue, maxTree[b]);
                    b--;
                }
                a /= 2;
                b /= 2;
            }
            io.println(minValue + " " + maxValue);
            
            
            questions--;
        }
        io.close();

    }
}
