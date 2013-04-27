/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 15, molecularweight
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class MolecularWeight {
    // Abstract Data Type "Entry"
    public static final class Entry {
        private final int atomicNumber;
        private final double atomicWeight;
        private final String symbol;
        private final String name;

        // Entry Constructor
        public Entry(final int aNumber, final double aWeight,
                final String aSymbol, final String aName) {
            atomicNumber = aNumber;
            atomicWeight = aWeight;
            symbol = aSymbol;
            name = aName;

        }

        @Override
        public String toString() {
            return String.format("%d %f %s %s ", atomicNumber, atomicWeight,
                    symbol, name);
        }
    }

    public static void main(final String[] args) throws IOException {
        final Scanner fileIn = new Scanner(new File(args[0])).useDelimiter(",");
        final Scanner stdin = new Scanner(System.in);
        // Use one ArrayList to store the element information
        final ArrayList<Entry> elements = new ArrayList<Entry>();
        fileIn.nextLine();
        // As long as the file has more, populate the elements list
        while (fileIn.hasNext()) {
            final String name = fileIn.next();
            final int aNumber = Integer.parseInt(fileIn.next());
            final String symbol = fileIn.next();
            final double weight = Double.parseDouble(fileIn.next());
            fileIn.nextLine();
            elements.add(new Entry(aNumber, weight, symbol, name));
        }
        // Close the fileIn input
        fileIn.close();
        // A temporary ArrayList to store the molecular equation
        final ArrayList<String> temp = new ArrayList<String>();
        double sum = 0.0;
        int numItems = 0;

        while (stdin.hasNext()) {

            if (stdin.hasNextInt()) {
                final int mult = stdin.nextInt();
                temp.add("" + mult);
                final String elem = temp.get(numItems - 1);
                final int index = findElement(elements, elem);
                sum += (mult - 1) * (elements.get(index).atomicWeight);
                numItems++;
            } else {
                final String input = stdin.next();
                if (input.equals(".")) {
                    if (sum == 0.0) {
                        System.out.printf("Unknown Molecular equation%n");
                    } else {
                        String equation = "";
                        for (int i = 0; i < temp.size(); i++) {
                            equation += temp.get(i) + " ";
                        }
                        System.out.printf("Molecular weight of %s= %01.2f%n",
                                equation, sum);
                        sum = 0.0;
                        numItems = 0;
                        temp.clear();
                    }
                } else {

                    temp.add(input);
                    final int index = findElement(elements, input);

                    if (index < 0) {
                        System.out.println("Unknown Molecular equation");
                        String dummy = stdin.next();
                        while (!dummy.equals(".")) {
                            dummy = stdin.next();
                        }
                        temp.clear();

                    } else {
                        sum += elements.get(index).atomicWeight;
                    }
                    numItems++;
                }
            }
        }
    }

    public static int findElement(final ArrayList<Entry> elements,
            final String symbol) {
        for (int i = 0; i < elements.size(); i++) {
            final Entry temp = elements.get(i);
            if (temp.symbol.equals(symbol)) {

                return i;
            }
        }

        return -1;
    }
}
