/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 24, evaluator
 */
import java.util.ArrayDeque;
import java.util.Scanner;

public class Evaluator {
    // Two stacks that keep track of the operators and operands
    public static final ArrayDeque<String> OPERANDS = new ArrayDeque<String>();
    public static final ArrayDeque<Double> NUMBERS = new ArrayDeque<Double>();

    public static void main(final String[] args) {
        // initialize a Scanner to read in the equation as a whole
        final Scanner stdin = new Scanner(System.in);

        // As long as their is an equation to be read, read it
        while (stdin.hasNextLine()) {
            // Store the equation in a String variable
            final String input = stdin.nextLine();
            // Initialize another Scanner to parse through the equation String
            final Scanner line = new Scanner(input);
            while (line.hasNext()) {
                // Assign each piece of the equation to a String variable
                final String item = line.next();

                if (item.equals("(")) {
                    continue;
                } else if (item.equals("+")) {
                    OPERANDS.push(item);
                } else if (item.equals("-")) {
                    OPERANDS.push(item);
                } else if (item.equals("*")) {
                    OPERANDS.push(item);
                } else if (item.equals("/")) {
                    OPERANDS.push(item);
                } else if (item.equals("sqrt")) {
                    OPERANDS.push(item);
                } else if (item.equals("^")) {
                    OPERANDS.push(item);
                } else if (item.equals("abs")) {
                    OPERANDS.push(item);
                } else if (item.equals(")")) {
                    rightParen();
                } else {
                    NUMBERS.push(Double.parseDouble(item));
                }
            }
            System.out.printf("\n%.2f", NUMBERS.pop());
            OPERANDS.clear();
            NUMBERS.clear();
        }
    }

    private static void rightParen() {
        final String op = OPERANDS.pop();
        double value = NUMBERS.pop();
        if (op.equals("+")) {
            value = NUMBERS.pop() + value;
        } else if (op.equals("-")) {
            value = NUMBERS.pop() - value;
        } else if (op.equals("*")) {
            value = NUMBERS.pop() * value;
        } else if (op.equals("/")) {
            value = NUMBERS.pop() / value;
        } else if (op.equals("sqrt")) {
            value = Math.sqrt(value);
        } else if (op.equals("^")) {
            value = Math.pow(NUMBERS.pop(), value);
        } else if (op.equals("abs")) {
            value = value * -1;
        }
        NUMBERS.push(value);
    }

}
