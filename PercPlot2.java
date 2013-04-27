/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 11, percolation
 */
import java.util.Random;

public class PercPlot2 {

    private static final double SIZE_SMALL = .005;
    private static final int M = 10000;
    private static final Random RNG = new Random(Long.getLong("seed",
            System.nanoTime()));

    // recursive curve plotting
    public static void curve(final int n, final double x0, final double y0,
            final double x1, final double y1, final Random rnd) {
        final double gap = .01;
        final double err = .0025;
        final double xm = (x0 + x1) / 2;
        final double ym = (y0 + y1) / 2;
        final double fxm = Estimate2.eval(n, xm, M, rnd);
        if (((x1 - x0) < gap) || (Math.abs(ym - fxm) < err)) {
            StdDraw.line(x0, y0, x1, y1);
            return;
        }
        curve(n, x0, y0, xm, fxm, rnd);
        StdDraw.filledCircle(xm, fxm, SIZE_SMALL);
        curve(n, xm, fxm, x1, y1, rnd);
    }

    // test client
    public static void main(final String[] args) {
        final int n = Integer.parseInt(args[0]);

        PercPlot2.curve(n, 0.0, 0.0, 1.0, 1.0, RNG);
    }
}
