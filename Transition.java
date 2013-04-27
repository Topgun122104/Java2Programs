/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 08, web
 */
import java.util.Scanner;

public class Transition {

    private static final int ONE = 1;

    public static void main(final String[] args) {

        final Scanner stdIn = new Scanner(System.in);
        final double link = Double.parseDouble(args[0]);

        final double leap = ONE - link;
        final int n = stdIn.nextInt();
        // number of pages
        final int[][] counts = new int[n][n]; // counts[i][j] = # links from
                                              // page i to page j
        final int[] outDegree = new int[n]; // outDegree[j] = # links from page
                                            // i to anywhere
        // Accumulate link counts.
        while (stdIn.hasNextInt()) {

            final int i = stdIn.nextInt();
            final int j = stdIn.nextInt();
            outDegree[i]++;
            counts[i][j]++;
        }

        System.out.println(n + " " + n);
        // Print probability distribution for row i.
        for (int i = 0; i < n; i++) {
            double p = 0.0;
            // Print probability for column j.
            for (int j = 0; j < n; j++) {
                if (outDegree[i] != 0) {
                    p = ((link * counts[i][j]) / outDegree[i]) + (leap / n);
                } else {
                    p = ONE / (double) n;

                }
                System.out.printf("%7.5f ", p);
            }
            System.out.println();
        }
    }
}
