/*
 * Author: Kenneth Truex, ktruex2012t@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 20, performance
 */
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Test the performance differences when using selection sort on a linked list
// vs. array list
public class SortTest {
    private static final int NUM_TRIALS = 26;
    // Initialize a random number generator to feed to Collections.shuffle
    private static final Random RNG = new Random(Long.getLong("seed",
            System.nanoTime()));

    public static void main(final String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        // size represents the quantity as well as the upper bound on the
        // numbers
        final int size = Integer.parseInt(args[0]);
        // determine whether the data structure is a linked list or array list
        final Class<?> clazz = Class.forName(args[1]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>) clazz
                .newInstance();
        // populate the list
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        // shuffle and sort using the stopwatch class to keep time
        final StopWatch sw = new StopWatch(true);
        for (int i = 0; i < NUM_TRIALS; i++) {
            sw.start();
            Collections.shuffle(list, RNG);
            sort(list);
            sw.stop();
        }
        // calculate the average time among all 25 trials
        final double averageTime = sw.getAverageTime();
        System.out.println(averageTime);

    }

    // Selection Sort
    public static void sort(final List<Integer> list) {
        for (int i = 0; i < (list.size() - 1); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) < list.get(min)) {
                    min = j;
                }
            }
            final int temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }
}
