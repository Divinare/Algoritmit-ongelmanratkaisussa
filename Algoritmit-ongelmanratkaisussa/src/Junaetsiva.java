
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Junaetsiva {

    public static void main(String[] args) {

        IO io = new IO();

        int n = io.nextInt();
        long[] carriages = new long[n];
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            carriages[i] = io.nextLong();
        }
        sum[0] = carriages[0];
        //  io.print("\n" + sum[0] + " ");
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + carriages[i];
            //        io.print(sum[i] + " ");
        }
        //    io.print("\n");
        int questions = io.nextInt();
        for (int i = 0; i < questions; i++) {
            int type = io.nextInt();
            if (type == 1) {
                int x = io.nextInt();
                long d = io.nextLong();
                sum[x-1] -= d;
            }
            if (type == 2) {
                int start = io.nextInt();
                int end = io.nextInt();
                if (start == 1) {
                    io.println(sum[end-1]);
                } else {
                    io.println(sum[end-1] - sum[start-2]);
                }
            }
        }
        io.close();
    }
}
