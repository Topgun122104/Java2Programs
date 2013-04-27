/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 19, interface
 */
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SelectionSort {
    // create a new random number generator object
    private static final Random RNG = new Random(Long.getLong("seed",
            System.nanoTime()));

    public static void main(final String[] args) {
        // Take the size of the list(also upper bound of numbers) from the
        // command line
        final int size = Integer.parseInt(args[0]);
        final List<Integer> myList = new MyArrayList(size);
        // Populate the list with the values 1-size
        for (int i = 1; i <= size; i++) {
            myList.add(new Integer(i));
        }
        // Use Collections.shuffle() in order to shuffle the list and prepare
        // for sorting
        Collections.shuffle(myList, RNG);
        System.out.printf("%s%n", "Shuffled:");
        System.out.println(myList.toString());
        // Use selection sort to sort the shuffled list
        sort(myList);
        System.out.printf("%s%n", "Sorted:");
        System.out.println(myList.toString());

    }

    // Implement Selection Sort in order to sort the list in ascending order
    public static void sort(final List<Integer> data) {

        for (int i = 0; i < data.size(); i++) {
            int min = i;

            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j) < data.get(min)) {
                    min = j;
                }
            }
            final int temp = data.get(i);
            data.set(i, data.get(min));
            data.set(min, temp);
        }

    }

}
