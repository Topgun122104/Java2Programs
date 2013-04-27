/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 25, guitar
 */

import java.util.ArrayDeque;
import java.util.Random;

public class GuitarString {
    public static final double EDF = 0.996;
    public static final double HALF = 0.5;
    public static final double SAMPLING_RATE = 44100;
    private static final Random RNG = new Random(Long.getLong("seed",
            System.nanoTime()));
    double theFrequency;
    ArrayDeque<Double> notes;

    // create a guitar string of the given frequency and initialize the
    // ArrayDeque
    GuitarString(final double frequency) {
        notes = new ArrayDeque<Double>();
        theFrequency = frequency;
    }

    // set the buffer to white noise
    final void pluck() {
        final int n = (int) (SAMPLING_RATE / theFrequency);
        for (int i = 0; i < n; i++) {
            final double temp = ((RNG.nextDouble() * (HALF + HALF)) - HALF);
            notes.push(temp);
        }

    }

    // advance the simulation one time step
    final void tic() {
        final double first = notes.peekFirst();
        notes.removeFirst();
        final double second = notes.peekFirst();
        final double average = (first + second) / 2;
        final double result = average * EDF;
        notes.addLast(result);
    }

    // return the current sample
    final double sample() {
        return notes.getLast();
    }

}
