/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 13, bigdecimal
 */
import java.math.BigDecimal;
import java.util.Scanner;

public class Mortgage {

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);
        // Take in the starting balance and interest rate from the command line
        BigDecimal balance = new BigDecimal(args[0]);
        final BigDecimal interest = new BigDecimal(args[1]);

        while (stdin.hasNext()) {
            final String input = stdin.next().toLowerCase();
            if (input.equals("balance")) {
                printBalance(balance.setScale(2, BigDecimal.ROUND_HALF_UP));
            } else {
                final BigDecimal payment = new BigDecimal(input);
                balance = balance.add(balance.multiply(interest));
                balance = balance.subtract(payment);
            }
        }

    }

    public static void printBalance(final BigDecimal balance) {
        // Establish a BigDecimal "0" in order to compare balance
        final BigDecimal zero = new BigDecimal("0");
        if (balance.compareTo(zero) == 1) {
            System.out.println("Balance: " + balance.toString() + " left");
        } else if (balance.compareTo(zero) == -1) {
            System.out
                    .println("Balance: " + balance.abs().toString() + " over");
        } else {
            System.out.println("Balance: " + balance.toString());
        }
    }

}
