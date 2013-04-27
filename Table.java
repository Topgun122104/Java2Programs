/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 05, Output
 */

// Output a table that is 100% identical to the one on the class lab webpage
public class Table {

    private static final int TWO = 2;
    private static final int ONE_HUNDRED = 100;

    public static void main (final String[] args) {
        // Accept a command line argument for the number of rows in the table
        final int N = Integer.parseInt (args[0]);
        // Process each row of the table from 1 to N
        for (int i = 1; i <= N; i++) {
            // Create an empty string d which will be used to hold the number of
            // d's per row
            String d = "";
            // Calculate the number of digits in the square of i
            final double numDigits = (Math.floor (Math.log10 (i * i))) + 1;
            // for each digit in the square of i, add the letter "d" to the
            // string d
            for (int j = 0; j < numDigits; j++) {
                d += "d";
            }
            // Print the table to the screen following the format necessary to
            // create an identical table
            System.out.printf (":%-5s %7d 0x%04x %9d %,9d %9.3f %8.0f%% %5s%s",
                    d, i, i, i * i, i * i * i,
                    (Math.log10 (i * i) / Math.log10 (TWO)),
                    ((i / (double) N) * ONE_HUNDRED), d, ":");
            System.out.println ();
        }

    }

}

