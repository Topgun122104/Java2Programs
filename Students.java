/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 06, input
 */
import java.util.Scanner;

public class Students {

    private static final int SIXTY = 60;
    private static final int SEVENTY = 70;
    private static final int EIGHTY = 80;
    private static final int NINETY = 90;

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);
        // output the column names of the table
        System.out.printf("%-16s%-13s%-6s", "Name", "Grade", "Letter");
        // Create these two variables to be used for calculating the average
        int total = 0;
        int numStudents = 0;
        // As long as there is another student's information, read in that
        // information
        while (stdin.hasNext()) {
            final String fName = stdin.next();
            final String lName = stdin.next();
            final int numGrade = stdin.nextInt();
            final String letterGrade = calcLetter(numGrade);
            total += numGrade;
            numStudents++;
            // Format and display the output in the table
            System.out.printf("%-16s%5d%9s%n", fName + " " + lName, numGrade,
                    letterGrade);
        }
        // After reading all student information, calculate and display the
        // average
        final double average = calcAverage(total, numStudents);
        System.out.println();
        System.out.printf("%-15s%6.2f ", "Average", average);

    }

    // Turn the number grade into a letter grade
    public static String calcLetter(final int numGrade) {
        if (numGrade >= NINETY) {
            return "A";
        } else if (numGrade >= EIGHTY) {
            return "B";
        } else if (numGrade >= SEVENTY) {
            return "C";
        } else if (numGrade >= SIXTY) {
            return "D";
        } else {
            return "F";
        }
    }

    // Calculate the average grade given all the student's grades
    public static double calcAverage(final int total, final int numStudents) {
        return ((double) total / (double) numStudents);
    }

}
