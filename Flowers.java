/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Final Project, flowers2
 */
import java.util.Arrays;
import java.util.Scanner;

public class Flowers {
    // Create a data structure to store the time to get to each cow as well as
    // the amount of flowers each cow eats per unit time
    static class Destruction implements Comparable<Destruction> {
        private final int time;
        private final int destroyed;

        public Destruction(final int theTime, final int theDestroyed) {
            time = theTime;
            destroyed = theDestroyed;
        }

        @Override
        // Since we multiply by 2 in the find min flowers method, there is no
        // need to change this method from Homework other than the names of the
        // objects
        public int compareTo(final Destruction that) {
            final int result = ((destroyed * that.time) - (time * that.destroyed));
            return result;
        }
    }

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);
        // Read in the number of cows from the standard input
        final int numCows = stdin.nextInt();
        assert numCows >= 0;
        // Initialize an array of destruction objects with a length equal to the
        // number of cows.
        final Destruction[] list = new Destruction[numCows];
        // For each cow, create a Destruction object and initialize it with the
        // time and flowers destroyed given.
        for (int i = 0; i < numCows; i++) {
            final int tempTime = stdin.nextInt();
            final int tempDestroyed = stdin.nextInt();
            assert tempTime >= 0;
            assert tempDestroyed >= 0;
            list[i] = new Destruction(tempTime, tempDestroyed);
        }
        // Sport the Destruction array based on our overriden compareTo method
        Arrays.sort(list);

        final long minFlowers = findMinFlowers(list);
        assert minFlowers >= 0;
        System.out.println(minFlowers);
        stdin.close();
    }

    // The biggest number possible is when there are 100,000 cows and they each
    // take 2,000,000 time units. That will overflow integer so we need to make
    // sure we use the long primitive data type
    public static long findMinFlowers(final Destruction[] list) {
        long total = 0;

        for (int i = 0; i < list.length; i++) {
            long amount = 0;
            for (int j = 0; j < i; j++) {
                amount += list[j].destroyed;
            }
            total += (2 * list[i].time) * amount;
        }
        return total;
    }

}
