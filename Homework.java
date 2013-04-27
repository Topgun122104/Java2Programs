/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 26, homework
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Homework {

    static class Problem implements Comparable<Problem> {

        private final int time;
        private final int weight;

        public Problem(final int theTime, final int theWeight) {
            time = theTime;
            weight = theWeight;
        }

        @Override
        // If you take the two Problem objects to be compared and look at the
        // two possible outcomes of the comparison, the formula you will come up
        // with what is coded below
        public int compareTo(final Problem that) {
            final int result = ((time * that.weight) - (weight * that.time));
            return result;
        }

    }

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);
        // Read in the number of test cases
        final int cases = stdin.nextInt();
        assert cases >= 0;
        // Initialize main loop based on number of test cases
        for (int i = 1; i <= cases; i++) {
            final int numProbs = stdin.nextInt();
            assert numProbs >= 0;
            // Due to the input format, we must store the values of the weights
            // and times and create the instances in a separate loop
            final int[] probWeights = new int[numProbs];
            final int[] probTimes = new int[numProbs];
            // Initialize an array of Problem objects
            final Problem[] list = new Problem[numProbs];

            // Read in the problem times
            for (int j = 0; j < numProbs; j++) {
                probTimes[j] = stdin.nextInt();
            }
            // Read in the problem weights
            for (int j = 0; j < numProbs; j++) {
                probWeights[j] = stdin.nextInt();
            }

            // Take the times and weights, create Problem objects, and store
            // them in the proper array
            for (int j = 0; j < numProbs; j++) {
                list[j] = new Problem(probTimes[j], probWeights[j]);
            }

            Collections.sort(Arrays.asList(list));

            final int answer = findMinWeight(list);
            assert answer >= 0;
            System.out.println(answer);
        }

    }

    // Iterate through the sorted array of Problem objects and calculate the
    // minimum weight
    public static int findMinWeight(final Problem[] list) {
        int weight = 0;

        for (int i = 0; i < list.length; i++) {
            int time = 0;
            for (int j = 0; j <= i; j++) {
                time += list[j].time;
            }
            weight += list[i].weight * time;
        }

        return weight;

    }

}
