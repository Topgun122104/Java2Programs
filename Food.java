/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 17, snake
 */
import java.util.Random;

// Randomly generates a food item
public class Food {
    // RNG cutoff for positive or negative coordinate
    private static final double POINT_FIVE = .5;
    // Radius of food
    private static final int F_RADIUS = 20;
    // X Coordinate of food item
    public static int foodx;
    // Y Coordinate of food item
    public static int foody;
    private final int length = 310;
    private final int height = 350;
    private static final Random RNG = new Random();

    Food() {
        foodx = getX();
        foody = getY();
    }

    public final int getX() {
        return randomize(RNG.nextInt(length + 1));
    }

    public final int getY() {
        return randomize(RNG.nextInt(height + 1));
    }

    // Ensures that the food item will be consumed entirely by snake
    private int randomize(final int value) {
        int newValue = value;
        final int mod = newValue % F_RADIUS;
        final int midpoint = (int) Math.floor(F_RADIUS / 2);
        if (mod > midpoint) {
            final int difference = F_RADIUS - mod;
            newValue += difference;
        } else {
            newValue -= mod;
        }

        if (RNG.nextDouble() > POINT_FIVE) {
            return (newValue);
        } else {
            return (0 - newValue);
        }
    }

}
