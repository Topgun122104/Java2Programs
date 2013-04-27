/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 25, guitar
 */
import java.util.Scanner;

public class GuitarHero {
    public static final int TWELVE = 12;
    public static final int FREQUENCY = 44100;

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);
        while (stdin.hasNext()) {
            final double note = stdin.nextDouble();
            final double duration = stdin.nextDouble();
            final double freq = (440 * Math.pow(2, (note / TWELVE)));
            final GuitarString string = new GuitarString(freq);
            final double[] buffer = new double[(int) (FREQUENCY * duration)];
            string.pluck();
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = string.sample();
                string.tic();
            }
            StdAudio.play(buffer);

        }
        stdin.close();
    }
}
