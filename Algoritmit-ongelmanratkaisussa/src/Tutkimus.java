/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Joe
 * Course: Algorithms in problem solving
 */

public class Tutkimus {
    
    private static int[][] numbers;
    
private static int decrypt(int a, int b, int c, int d) {
	for(int i = 0; i < 10000; ++i) {
		c *= a; d ^= a; c *= b; a ^= c; d += b; a += c;
		b -= a; b *= c; c ^= d; c *= d; d ^= a; c += b;
		a ^= c; d -= b; c += b; b *= a; d -= a; c -= d;
		a += c; c ^= b; b *= a; a ^= d; c -= d; d -= a;
		c -= a; d -= b; c += d; b -= c; c += b; c += d;
		b *= c; b -= d; b *= a; b ^= c; d -= b; d -= a;
	}
	
	while(a < 0) a -= 1000000000;
	a %= 1000000;
	return a - 500000;
}

private static int d(int index) {
    return decrypt(numbers[index][0], numbers[index][1], numbers[index][2], numbers[index][3]);
}
    
    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();
        int x = decrypt(232376, 383233, 639282, 763757);
        numbers = new int[n][4];
        for(int i = 0; i < n; i++) {
            numbers[i][0] = io.nextInt();
            numbers[i][1] = io.nextInt();
            numbers[i][2] = io.nextInt();
            numbers[i][3] = io.nextInt(); 
        }
        int lowIndex = 0;
        int highIndex = n-1;
        
        int record = -9999999;
        int recordIndex = 0;
        int current;
        while(lowIndex <= highIndex) {
            int middle = (lowIndex+highIndex)/2;
            current = d(middle);
            if(current > record) {
                record = current;
                recordIndex = middle;
            }
            if (d(middle-1) > current) {
                highIndex = middle-1;
            } else {
                lowIndex = middle+1;
            }
        }
        io.print(recordIndex);
        io.close();

    }
}
