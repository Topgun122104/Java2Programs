/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 16, stock
 */
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public final class StockPrice {

    private static final int NEG_HUNDRED = -100;

    static class Entry {
        private final Date date;
        private final BigDecimal open;
        private final BigDecimal high;
        private final BigDecimal low;
        private final BigDecimal close;
        private final BigDecimal volume;
        private final BigDecimal aClose;

        Entry(final Date sDate, final BigDecimal sOpen, final BigDecimal sHigh,
                final BigDecimal sLow, final BigDecimal sClose,
                final BigDecimal sVol, final BigDecimal sAClose) {
            date = sDate;
            open = sOpen;
            high = sHigh;
            low = sLow;
            close = sClose;
            volume = sVol;
            aClose = sAClose;
        }

    }

    public static void main(final String[] args) throws IOException,
            ParseException {
        final Scanner stdin = new Scanner(System.in);
        final Scanner fileIn = new Scanner(new File(args[0]))
                .useDelimiter("[\\s,]");
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, NEG_HUNDRED);
        final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
        df.set2DigitYearStart(cal.getTime());

        final ArrayList<Entry> stocks = new ArrayList<Entry>();
        fileIn.nextLine();
        while (fileIn.hasNext()) {
            final String date = fileIn.next();
            final Date fDate = df.parse(date);
            final BigDecimal open = new BigDecimal(fileIn.next());
            final BigDecimal high = new BigDecimal(fileIn.next());
            final BigDecimal low = new BigDecimal(fileIn.next());
            final BigDecimal close = new BigDecimal(fileIn.next());
            final BigDecimal volume = new BigDecimal(fileIn.next());
            final BigDecimal aClose = new BigDecimal(fileIn.next());
            stocks.add(new Entry(fDate, open, high, low, close, volume, aClose));

        }

        while (stdin.hasNext()) {
            final Date sDate = df.parse(stdin.next());
            final Date eDate = df.parse(stdin.next());

            if (valiDate(sDate, eDate) < 0) {
                System.out.format("No Data %n");
            } else if ((findDate(stocks, sDate) == -1)
                    || (findDate(stocks, eDate) == -1)) {
                System.out.format("No Data %n");
            } else {
                final int startI = findDate(stocks, sDate);
                final int endI = findDate(stocks, eDate);

                System.out.print(openAv(stocks, startI, endI) + ","
                        + highAv(stocks, startI, endI) + ","
                        + lowAv(stocks, startI, endI) + ","
                        + closeAv(stocks, startI, endI) + ","
                        + volumeAv(stocks, startI, endI) + ","
                        + aCloseAv(stocks, startI, endI));
                System.out.println();

            }
        }

    }

    public static int valiDate(final Date date1, final Date date2) {
        return date2.compareTo(date1);
    }

    public static int findDate(final ArrayList<Entry> stocks, final Date target) {
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).date.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static BigDecimal openAv(final ArrayList<Entry> stocks,
            final int end, final int start) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = start; i <= end; i++) {
            sum = sum.add(stocks.get(i).open);
        }
        sum = sum.divide(new BigDecimal((end - start) + 1), 2,
                BigDecimal.ROUND_HALF_UP);

        return sum;
    }

    public static BigDecimal highAv(final ArrayList<Entry> stocks,
            final int end, final int start) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = start; i <= end; i++) {
            sum = sum.add(stocks.get(i).high);
        }
        sum = sum.divide(new BigDecimal((end - start) + 1), 2,
                BigDecimal.ROUND_HALF_UP);

        return sum;
    }

    public static BigDecimal lowAv(final ArrayList<Entry> stocks,
            final int end, final int start) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = start; i <= end; i++) {
            sum = sum.add(stocks.get(i).low);
        }
        sum = sum.divide(new BigDecimal((end - start) + 1), 2,
                BigDecimal.ROUND_HALF_UP);

        return sum;
    }

    public static BigDecimal closeAv(final ArrayList<Entry> stocks,
            final int end, final int start) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = start; i <= end; i++) {
            sum = sum.add(stocks.get(i).close);
        }
        sum = sum.divide(new BigDecimal((end - start) + 1), 2,
                BigDecimal.ROUND_HALF_UP);

        return sum;
    }

    public static BigDecimal volumeAv(final ArrayList<Entry> stocks,
            final int end, final int start) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = start; i <= end; i++) {
            sum = sum.add(stocks.get(i).volume);
        }
        sum = sum.divide(new BigDecimal((end - start) + 1), 2,
                BigDecimal.ROUND_HALF_UP);

        return sum;
    }

    public static BigDecimal aCloseAv(final ArrayList<Entry> stocks,
            final int end, final int start) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = start; i <= end; i++) {
            sum = sum.add(stocks.get(i).aClose);
        }
        sum = sum.divide(new BigDecimal((end - start) + 1), 2,
                BigDecimal.ROUND_HALF_UP);

        return sum;
    }

}

