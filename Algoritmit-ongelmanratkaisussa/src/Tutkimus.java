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
    
    private static int decrypt(int a, int b, int c, int d) {
	for(int i = 0; i < 1; ++i) {
		c *= a; d ^= a; c *= b; a ^= c; d += b; a += c;
		b -= a; b *= c; c ^= d; c *= d; d ^= a; c += b;
		a ^= c; d -= b; c += b; b *= a; d -= a; c -= d;
		a += c; c ^= b; b *= a; a ^= d; c -= d; d -= a;
		c -= a; d -= b; c += d; b -= c; c += b; c += d;
		b *= c; b -= d; b *= a; b ^= c; d -= b; d -= a;
	}
	 IO io = new IO();
         io.print(a);
	while(a < 0) a -= 1000000000;
	a %= 1000000;
	return a - 500000;
}
    
    public static void main(String[] args) {

        IO io = new IO();
        
        io.close();

    }
}
