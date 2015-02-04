
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Kirjat {

    private static class Quicksort {

        private int array[];
        private int length;

        public void sort(int[] inputArray) {
            if (inputArray == null || inputArray.length == 0) {
                return;
            }
            this.array = inputArray;
            length = inputArray.length;
            quickSort(0, length - 1);
        }

        private void quickSort(int lowerIndex, int higherIndex) {
            int i = lowerIndex;
            int j = higherIndex;
            int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
            while (i <= j) {
                while (array[i] < pivot) {
                    i++;
                }
                while (array[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    exchangeNumbers(i, j);
                    i++;
                    j--;
                }
            }
            if (lowerIndex < j) {
                quickSort(lowerIndex, j);
            }
            if (i < higherIndex) {
                quickSort(i, higherIndex);
            }
        }

        private void exchangeNumbers(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {

        IO io = new IO();
        int n = io.nextInt();
        int[] books = new int[n];
        for (int i = 0; i < n; i++) {
            books[i] = io.nextInt();
        }
        Quicksort sorter = new Quicksort();
        sorter.sort(books);

        if (n == 1) {
            io.print(books[0] * 2);
            io.close();
            return;
        }

        int maijaIndex = 0;
        long maijaTime = books[maijaIndex];
        int uoleviIndex = n - 1;
        long uoleviTime = books[uoleviIndex];

        long difference = 0;
        long totalTime = 0;

        for (int i = 0; i < books.length; i++) {
            totalTime += books[i];
        }

        while (true) {
            if (maijaTime <= uoleviTime) {
                maijaIndex++;
                if (maijaIndex < uoleviIndex) {
                    maijaTime += books[maijaIndex];
                }
            } else {
                uoleviIndex--;
                if (uoleviIndex > maijaIndex) {
                    uoleviTime += books[uoleviIndex];
                }
            }
            if (maijaIndex > uoleviIndex) {
                if (maijaTime == uoleviTime) {
                    io.println(totalTime);
                    break;
                } else {
                 //   io.println("b 0: " + books[0] + " maijan aika " + maijaTime + " \n");
               //     io.print("b n -1: " + books[n - 1] + " uolevin aika " + uoleviTime + "\n");
                    if (books[0] >= maijaTime || books[n - 1] >= uoleviTime) {

                        difference = Math.abs(maijaTime - uoleviTime);

                        io.print(totalTime + difference);
                     //   io.print("  total time " + totalTime + " diff " + difference);

                        break;
                    } else {
                        io.println(totalTime);
                        break;
                    }
                }
            }
        }

        io.close();

    }
}
