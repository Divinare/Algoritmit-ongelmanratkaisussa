
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class LautaAita {

    private static class Entry implements Comparable<Entry> {

        public int value;
        public int index;

        public Entry(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Entry o) {
            if (this.value > o.value) {
                return 1;
            } else if (this.value == o.value) {
                return 0;
            } else {
                return -1;
            }
        }

        public String toString() {
            return "" + value;
        }
    }

    public static void main(String[] args) {

        boolean debug = false;

        IO io = new IO();

        int boards = io.nextInt();

        ArrayList<Entry> entries = new ArrayList<Entry>();
        Entry[] sortedTable = new Entry[boards];
        for (int i = 0; i < boards; i++) {
            Entry e = new Entry(io.nextInt(), i);
            entries.add(e);
            sortedTable[i] = e;
        }
        Arrays.sort(sortedTable);
        //     for (int i = 0; i < sortedTable.length; i++) {
        //        io.print(sortedTable[i].value + " ");
        //    }

        int smallestIndex = 0;
        int start = 0;
        int end = boards - 1;
        int timeUsed = 0;
        
      //  int[] sTree = new int[2*n];

        int laps = 0;
        while (true) {

            int cIndex = sortedTable[smallestIndex].index;
            int cValue = sortedTable[smallestIndex].value;

            if (debug) {
                io.println("cIndex : " + cIndex + " cValue: " + cValue);
                io.println("start: " + start + " end: " + end);
            }
            //find if currIndex value at start
            boolean inserted = false; // if the cValue key is already placed
            boolean found = false;
            int i = 0;
            while (true) {

                if (entries.get(i).value == cValue) {
                    if (debug) {
                        io.println("cValue " + cValue + " at start");
                    }
                    start++;
                    inserted = true;
                    break;
                } else if (entries.get(i).value > cValue) {
                    if (debug) {
                        io.println("at start, too big");
                    }
                    break;
                }
                i++;
            }
            if (found && !inserted) {

            }

            //find if currIndex value is at end
            if (!inserted) {
                i = entries.size() - 1;
                while (true) {
                    if (entries.get(i).value == cValue) {
                        if (debug) {
                            io.println("cValue " + cValue + " at end");
                        }
                        end--;
                        inserted = true;
                        break;
                    } else if (entries.get(i).value > cValue) {
                        //          io.println("at end, too big");
                        break;
                    }
                    i--;
                }
            }

            // inserting it now, didnt find cValue in either start or end
            if (!inserted) {
                boolean startCloser;
                int work = 0;
                int currIndex = 0;
                if (start <= (entries.size() - end)) {
                    for (int x = start; x < entries.size(); x++) {
                        if (entries.get(x).value == cValue) {
                            currIndex = x;
                        }
                    }
                } else {
                    for (int x = end; x >= 0; x--) {
                        if (entries.get(x).value == cValue) {
                            currIndex = x;
                        }
                    }
                }
                if(debug)
                io.println("curr index " + currIndex + " was finding " + cValue);
                //   io.println("curr index: " + currIndex);
                if ((currIndex - start) <= (end - cIndex)) {
                    work += currIndex - start;
                    startCloser = true;
                } else {
                    work += end - currIndex;
                    startCloser = false;
                }

                Entry e = entries.get(currIndex);
                if (startCloser) {
                    entries.remove(currIndex);
                    e.index = start;
                    entries.add(start, e);
                    start++;
                } else {
                    entries.remove(currIndex);
                    e.index = end;
                    entries.add(end, e);
                    end--;
                }
                timeUsed += work;
            }

            laps++;
            smallestIndex++;

            if (debug) {
                io.println(entries);
                io.println("work: " + timeUsed);
            }
            if (start >= end) {
                break;
            }
        }
        io.println(timeUsed);
        io.close();

    }
}
