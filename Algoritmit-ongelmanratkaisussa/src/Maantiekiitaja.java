
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Maantiekiitaja {

    private static class Stone {

        private int stoneFlies;
        private int mFlies;
        private int number;
        private boolean isStone;

        public Stone(int mFlies, int stoneFlies, int number) {
            this.mFlies = mFlies;
            this.stoneFlies = stoneFlies;
            this.number = number;
            this.isStone = isStone;
        }

        int getStuneFlies() {
            return this.stoneFlies;
        }

        int getMFlies() {
            return this.mFlies;
        }

        int getNumber() {
            return this.number;
        }

        public String toString() {
            return this.number + " mFlies: " + this.mFlies + " stoneFlies: " + this.stoneFlies;
        }
    }

    public static void main(String[] args) {

        IO io = new IO();

        int amountOfStones = io.nextInt();
        int collapses = io.nextInt();
        HashMap<Integer, Stone> objects = new HashMap<Integer, Stone>();
        //  List<Integer> stoneLocations = new ArrayList();
        //  int[] stoneLocations = new int[amountOfStones];
        for (int i = 0; i < amountOfStones; i++) {
            int location = io.nextInt();
            int mFlies = io.nextInt();
            int stoneFlies = io.nextInt();
            Stone s = new Stone(mFlies, stoneFlies, (i + 1));
            objects.put(location, s);
            // stoneLocations.add(location);
            //   stoneLocations[i] = location;
        }

        for (int i : objects.keySet()) {
            //      io.print(i + " \n");
        }
        for (Stone s : objects.values()) {
            //    io.print(s.toString() + "\n");
        }

        int collapsesCount = 0;
        int mLocation = 0;
        int boundary = 1000000000;
        TreeMap<Integer, Stone> stones = new TreeMap<Integer, Stone>(objects);
        // Objects x = new Objects(0 , 0, 0 , false);
        //    mapObjects.put(350 ,x);
        Entry<Integer, Stone> nextStoneLocation;
        while (collapsesCount < collapses) {

            if (stones.higherKey(mLocation) != null) {
                nextStoneLocation = stones.higherEntry(mLocation);
                stones.higherEntry(mLocation);
            } else {
                nextStoneLocation = stones.firstEntry();
            }
            // print the number of a hit stone
            io.print(nextStoneLocation.getValue().getNumber() + "\n");
            mLocation = nextStoneLocation.getKey();
            if (mLocation + nextStoneLocation.getValue().mFlies < 0) {
                mLocation = boundary + (mLocation + nextStoneLocation.getValue().mFlies);
            } else if (mLocation + nextStoneLocation.getValue().mFlies >= boundary) {
                mLocation = nextStoneLocation.getValue().mFlies - (boundary - mLocation);
            } else {
                mLocation += nextStoneLocation.getValue().mFlies;
            }

            if (stones.containsKey(mLocation)) {
                stones.remove(mLocation);
            }

            int sLocation = nextStoneLocation.getKey();
            // remove flying stone
            stones.remove(sLocation);

            if (sLocation + nextStoneLocation.getValue().stoneFlies < 0) {
                sLocation = boundary + (sLocation + nextStoneLocation.getValue().stoneFlies);
            } else if (sLocation + nextStoneLocation.getValue().stoneFlies >= boundary) {
                sLocation = nextStoneLocation.getValue().stoneFlies - (boundary - sLocation);
            } else {
                sLocation += nextStoneLocation.getValue().stoneFlies;
            }
            stones.put(sLocation, nextStoneLocation.getValue());

           // io.print("siirtymä oli: " + nextStoneLocation.getValue().mFlies + "\n");
        //    io.print("seurav: " + nextStoneLocation.getValue().number + "\n");
       //     io.print("mLoc " + mLocation + "\n");
         //   io.print("sLocation " + sLocation + "\n");
            collapsesCount++;

        }
        /*
         Iterator it = objects.entrySet().iterator();
         while (it.hasNext()) {

         Map.Entry pairs = (Map.Entry) it.next();
         System.out.println(pairs.getKey() + " = " + pairs.getValue());
         it.remove(); // avoids a ConcurrentModificationException
         }
         */
        io.close();

    }

}
