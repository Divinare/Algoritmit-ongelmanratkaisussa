
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
        for (int i = 0; i < amountOfStones; i++) {
            int location = io.nextInt();
            int mFlies = io.nextInt();
            int stoneFlies = io.nextInt();
            Stone s = new Stone(mFlies, stoneFlies, (i + 1));
            objects.put(location, s);
        }

        int collapsesCount = 0;
        int mLocation = 0;
        int boundary = 1000000000;
        TreeMap<Integer, Stone> stones = new TreeMap<Integer, Stone>(objects);

        Entry<Integer, Stone> nextStoneLocation;
        while (collapsesCount < collapses) {

            if (stones.higherKey(mLocation) != null) {
                nextStoneLocation = stones.higherEntry(mLocation);
                stones.higherEntry(mLocation);
            } else {
                nextStoneLocation = stones.firstEntry();
            }

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

            collapsesCount++;

        }
        io.close();

    }

}
