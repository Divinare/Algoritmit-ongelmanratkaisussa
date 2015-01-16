
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Joe Course: Algorithms in problem solving
 */
public class Asunnot {

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
        int m = io.nextInt();
        int k = io.nextInt();
        int[] wishes = new int[n];
        for (int i = 0; i < wishes.length; i++) {
            wishes[i] = io.nextInt();
        }
        Integer[] apartments = new Integer[m];
        for (int i = 0; i < apartments.length; i++) {
            apartments[i] = io.nextInt();
        }
        // Quicksort sorter = new Quicksort();
        Arrays.sort(wishes);
        Arrays.sort(apartments);

        int givenApartments = 0;
        int givenApartmentIndex = 0;
        int foundIndex;
        int mostLeftIndex = 0;
        for (int i = 0; i < wishes.length; i++) {
            int wish = wishes[i];

            foundIndex = Arrays.binarySearch(apartments, givenApartmentIndex, apartments.length, wish, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (Math.abs(o1 - o2) <= k) {
                        return 0;
                    } else {
                        return o1 - o2;
                    }
                }
            });
            if (foundIndex >= 0) {
                mostLeftIndex = foundIndex;

                while (foundIndex > 0 && foundIndex - 1 > givenApartmentIndex) {

                    foundIndex = Arrays.binarySearch(apartments, 0, foundIndex, wish, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            if (Math.abs(o1 - o2) <= k) {
                                return 0;
                            } else {
                                return o1 - o2;
                            }
                        }
                    });
                    if (foundIndex > 0) {
                        mostLeftIndex = foundIndex;
                    }
                }
                givenApartmentIndex = mostLeftIndex + 1;
             //   io.print("givenApartmentIndex: " + givenApartmentIndex + "\n");
                givenApartments++;
            }
        }
        io.print(givenApartments);
        io.close();

    }
}
