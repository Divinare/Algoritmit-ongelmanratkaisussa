/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Ruudukko {

    private static class Previous {
        
        Previous p;
        boolean root = false;
        char value;
        public Previous(char value) {
            this.value = value;
        }
        public void setPrevious(Previous p) {
            this.p = p;
        }
        public Previous getPrevious() {
            return this.p;
        }
        public char getValue() {
            return value;
        }
    }
    
    public static int getValue(char c) {
        return Character.getNumericValue(c);
    }

    public static void main(String[] args) {

        IO io = new IO();

        int n = io.nextInt();

        char t[][] = new char[n][n];
        String d[][] = new String[n][n];
        Previous[][] route = new Previous[n][n]; // where came from that square
        for (int i = 0; i < n; i++) {
            String line = io.next();
            for (int j = 0; j < n; j++) {
                t[i][j] = line.charAt(j);
                d[i][j] = "";
                route[i][j] = new Previous(line.charAt(j));
            }
        }

        // make route costs for top and left rows
        int y = 0;
        d[0][0] = "" + t[0][0];
        for (int i = 1; i < n; i++) {
            d[i][y] = (d[i - 1][y] + t[i][y]);
            route[i][y].setPrevious(route[i-1][y]);
        }
        int x = 0;
        for (int j = 1; j < n; j++) {
            d[x][j] = (d[x][j-1] + t[x][j]);
            route[x][j].setPrevious(route[x][j-1]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                String s1 = "";
                String s2 = "";
                if (i > 0) {
                    s1 = d[i - 1][j];
                }
                if (j > 0) {
                    s2 = d[i][j - 1];
                }
                if(s1.compareTo(s2) < 0) {
                    route[i][j].setPrevious(route[i-1][j]);
                    d[i][j] = d[i-1][j] + t[i][j];
                } else {
                    route[i][j].setPrevious(route[i][j-1]);
                    d[i][j] = d[i][j-1]+t[i][j];
                }
                
            }
        }
        
        String result = "";
        Previous start = route[n-1][n-1];
        result += start.getValue();
        while(start != null) {
            start = start.getPrevious();
            if(start != null) {
                result += start.getValue();
            }
        }
        for(int i = result.length()-1; i >= 0; i--) {
            io.print(result.charAt(i));
        }
        
        io.close();

    }
}
