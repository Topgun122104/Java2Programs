/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 21, josephus
 */
import java.util.List;
import java.util.ListIterator;

public class Josephus {

    private static final int NUM_TRIALS = 11;

    public static void main(final String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        // Take the initial number of soldiers and the skip count from the
        // command line
        final int size = Integer.parseInt(args[0]);
        final int skip = Integer.parseInt(args[1]);
        // Take the decision to use either array list or linked list from the
        // command line
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>) clazz
                .newInstance();
        final StopWatch sw = new StopWatch(true);
        // Perform a certain number of trials and take the average time
        // I had to perform 101 trials to get my Linked List to look linear
        for (int i = 0; i < NUM_TRIALS; i++) {
            sw.start();
            for (int j = 1; j <= size; j++) {
                list.add(j);
            }
            josephus(list, skip);
            sw.stop();
            list.clear();
        }
        final double averageTime = sw.getAverageTime();
        System.out.println(averageTime);

    }

    // Solution to the josephus problem
    public static int josephus(final List<Integer> list, final int skip) {
        int temp = 0;
        while (list.size() > 1) {

            final ListIterator<Integer> itr = list.listIterator();
            while (itr.hasNext()) {
                itr.next();
                temp++;
                if ((temp % skip) == 0) {
                    itr.remove();

                }

            }
        }
        return list.get(0);

    }

}
