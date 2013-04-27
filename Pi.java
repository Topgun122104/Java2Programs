/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: HW1, pi
 */

import java.util.Random;

public class Pi {

    private static final int TWO = 2;
    private static final int FOUR = 4;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    // Create a new Random Number Generator
    private static final Random RNG = new Random (Long.getLong ("seed",
            System.nanoTime ()));

    public static void main (final String[] args) {
        // Command line argument indicating how many darts will be thrown
        final int TOTAL_DARTS = Integer.parseInt (args[ZERO]);

        // Integer indicating how many darts are located inside the circle
        int dartsInCircle = ZERO;

        for (int i = ONE; i <= TOTAL_DARTS; i++) {
            // generate a random number x and y between 0.0 and 1.0
            final double x = RNG.nextDouble ();
            final double y = RNG.nextDouble ();

            if (isInCircle (x, y)) {
                // If the dart landed in the circle, increment dartsInCircle by one
                dartsInCircle += ONE;
            }
        }

        final double pi = (dartsInCircle / (double) TOTAL_DARTS) * FOUR;

        System.out.printf ("%.6f", pi);

    }

    // Test whether or not the dart landed inside the circle
    public static boolean isInCircle (final double x, final double y) {
        // result is based on the equation: x^2 + y^2 = 1
        final double distance = Math.pow (x, TWO) + Math.pow (y, TWO);

        return (distance <= ONE);
    }

}
