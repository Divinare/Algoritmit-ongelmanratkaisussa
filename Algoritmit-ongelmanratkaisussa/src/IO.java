import java.util.*;
import java.io.*;

public class IO extends PrintWriter {
	private BufferedReader r;
	private StringTokenizer s;
	
	public IO() {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String nextLine() {
		String s = null;
		try {
			s = r.readLine();
		} catch (Exception e) {}
		if(s == null) throw new NoSuchElementException();
		return s;
	}
	
	public String next() {
		while (s == null || !s.hasMoreElements()) {
			s = new StringTokenizer(nextLine());
		}
		return s.nextToken();
	}
	
	public int nextInt() {
		return Integer.parseInt(next());
	}
	
	public long nextLong() {
		return Long.parseLong(next());
	}
	
	public double nextDouble() {
		return Double.parseDouble(next());
	}
}