
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Elokuvat {

    public static void print(HashMap<Integer, Integer> printMap, IO io) {
        Iterator it = printMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            io.print("movieLength: " + pairs.getKey() + " index: " + pairs.getValue());
        }
        io.print("\n");
    }

    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();

        int[] movies = new int[n];
        for (int i = 0; i < n; i++) {
            movies[i] = io.nextInt();
        }
        int record = 0;
        int currentLength = 0;
        HashMap<Integer, Integer> addedMovies = new HashMap<Integer, Integer>(); // movieLength, index
        for (int i = movies.length-1; i >= 0; i--) {

            int movie = movies[i];
            Integer currentMovie = addedMovies.get(movie);
            if (currentMovie != null) {
                if (currentLength < currentMovie-i) {
                    currentLength++;
                    if (currentLength > record) {
                        record = currentLength;
                    }
                } else {
                    currentLength = currentMovie - i;
                }
                addedMovies.put(movie, i);
            } else {
                currentLength++;
                if (currentLength > record) {
                    record = currentLength;
                }
                addedMovies.put(movie, i);
            }
           //  io.print("addedMovies sisältö: \n");
            //   print(addedMovies, io);
        }
        io.print(record);
        io.close();
    }
}
