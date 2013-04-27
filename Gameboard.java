/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 17, snake
 */
import java.util.ArrayList;

import javax.swing.JOptionPane;

// Creates a new game screen with the given dimensions
public class Gameboard {
    // Green food point value
    private static final int GREEN_ADD = 5;
    private static final int FOUR = 4;
    private static final int THREE = 3;
    private static final int LENGTH = 640;
    private static final int HEIGHT = 720;
    // Snake speed which is based on difficulty
    private static int speed;
    // Score field coordinates
    private final int xScore = -290;
    private final int yScore = 380;
    // Number of pixels the snake moves per unit time
    private static final int MOVE_LENGTH = 20;
    // Length of snake required taking into account 2 food items
    private static final int WIN_TOTAL = (LENGTH / MOVE_LENGTH)
            * (HEIGHT / MOVE_LENGTH);
    // Radius of food item
    private static final int F_RADIUS = 10;
    private static final int ONE = 1;
    // 15 second blue food item time limit
    private static final int BLUE_TIME_LIMIT = 15000;
    // 10 second green food item time limit
    private static final int GREEN_TIME_LIMIT = 10000;
    // player score
    private static int score = 0;
    private static int moves = 0;
    // Length of snake
    private static int sLength;
    private static int xScale;
    private static int yScale;
    private static final int SNAKE_RADIUS = 10;
    private static int xHead = 0;
    private static int yHead = 0;
    private static int xFood1;
    private static int yFood1;
    private static int xFood2;
    private static int yFood2;
    private static long food1Time;
    private static long food2Time;
    private static Food food;
    // coordinates of individual elements of snake body
    private static ArrayList<Integer> xCoord = new ArrayList<>();
    private static ArrayList<Integer> yCoord = new ArrayList<>();

    Gameboard(final int difficulty) {
        // create game screen
        StdDraw.setCanvasSize(HEIGHT, LENGTH);
        xScale = LENGTH / 2;
        yScale = HEIGHT / 2;
        StdDraw.setXscale(-xScale, xScale);
        StdDraw.setYscale(-yScale, yScale);
        food = new Food();
        sLength = THREE;
        xHead = -(2 * MOVE_LENGTH);
        xCoord.add(xHead);
        yCoord.add(0);
        xCoord.add(-MOVE_LENGTH);
        yCoord.add(0);
        xCoord.add(0);
        yCoord.add(0);
        speed = difficulty;
        makeFood("blue");
        makeFood("green");
    }

    // actual drawing and moving of snake happens here
    public final boolean onCreate(final boolean inGame) {
        StdDraw.show(speed);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(xFood1, yFood1, F_RADIUS);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(xFood2, yFood2, F_RADIUS);
        StdDraw.setPenColor(StdDraw.RED);
        final String scoreTemp = String.format("Score: %d", score);
        StdDraw.text(xScore, yScore, scoreTemp);

        if (inGame) {
            moves++;
            for (int i = sLength - ONE; i > 0; i--) {
                xCoord.set(i, xCoord.get(i - ONE));
                yCoord.set(i, yCoord.get(i - ONE));
            }
            xCoord.set(0, xHead);
            yCoord.set(0, yHead);

        }

        for (int i = 0; i < sLength; i++) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(xCoord.get(i), yCoord.get(i), SNAKE_RADIUS);
        }
        // red border lines for snake.
        StdDraw.line(-xScale - SNAKE_RADIUS, -yScale - SNAKE_RADIUS, xScale
                + SNAKE_RADIUS, -yScale - SNAKE_RADIUS);
        StdDraw.line(-xScale - SNAKE_RADIUS, -yScale - SNAKE_RADIUS, -xScale
                - SNAKE_RADIUS, yScale + SNAKE_RADIUS);
        StdDraw.line(-xScale - SNAKE_RADIUS, yScale + SNAKE_RADIUS, xScale
                + SNAKE_RADIUS, yScale + SNAKE_RADIUS);
        StdDraw.line(xScale + SNAKE_RADIUS, -yScale - SNAKE_RADIUS, xScale
                + SNAKE_RADIUS, yScale + SNAKE_RADIUS);
        // after each move, update food timers
        foodTimer("blue");
        foodTimer("green");
        // after each move, check that the snake is still alive
        return checkPos();
    }

    // update the direction of snake when arrows are pressed by user
    public final void currentDir(final String button) {
        if (button.equals("up")) {
            yHead += MOVE_LENGTH;
        } else if (button.equals("down")) {
            yHead -= MOVE_LENGTH;
        } else if (button.equals("left")) {
            xHead -= MOVE_LENGTH;
        } else if (button.equals("right")) {
            xHead += MOVE_LENGTH;
        } else if (button.equals("")) {
            return;
        }
    }

    // Check the the snake has not run into the wall or himself
    // Also check if the user has won or has consumed a food item
    private static boolean checkPos() {
        // Has the snake run into the wall?
        if (((Math.abs(xHead)) > xScale) || ((Math.abs(yHead)) > yScale)) {
            final String message = String.format(
                    "You have run into the wall!!\nYour score is %d", score);
            JOptionPane.showMessageDialog(null, message, "GAME OVER!!", 2);
            return false;
        }
        if ((moves > 0) && (sLength > FOUR)) {
            // Has the snake run into itself?
            for (int i = 1; i < sLength; i++) {
                if ((xHead == xCoord.get(i)) && (yHead == yCoord.get(i))) {
                    final String message = String.format(
                            "You have run into yourself!!\nYour score is %d",
                            score);
                    JOptionPane.showMessageDialog(null, message, "GAME OVER!!",
                            2);
                    return false;
                }
            }
        }
        // has the user won?
        if (sLength == (WIN_TOTAL - 2)) {
            JOptionPane.showMessageDialog(null, null, "YOU WIN!!", 2);
            return false;
        }
        // Has the user consumed a food item?
        if ((xHead == xFood1) && (yHead == yFood1)) {
            score += 1;
            makeFood("blue");
            sLength++;
            xCoord.add(xHead);
            yCoord.add(yHead);
            return true;
        } else if ((xHead == xFood2) && (yHead == yFood2)) {
            score += GREEN_ADD;
            makeFood("green");
            sLength++;
            xCoord.add(xHead);
            yCoord.add(yHead);
            return true;
        }
        return true;
    }

    // Time before next food item is generated
    private static void foodTimer(final String color) {
        final long current = System.currentTimeMillis();
        if (color.equals("blue")) {
            if ((current - food1Time) > BLUE_TIME_LIMIT) {
                makeFood("blue");
            }
        } else if (color.equals("green")) {
            if ((current - food2Time) > GREEN_TIME_LIMIT) {
                makeFood("green");
            }

        }
    }

    // Generate the food item when the proper amount of time has elapsed
    private static void makeFood(final String color) {
        if (color.equals("blue")) {
            xFood1 = food.getX();
            yFood1 = food.getY();
            if ((xFood1 == xFood2) && (yFood1 == yFood2)) {
                makeFood("blue");
            } else {
                for (int i = 0; i < sLength; i++) {
                    if ((xFood1 == xCoord.get(i)) && (yFood1 == yCoord.get(i))) {
                        makeFood("blue");
                        return;
                    }
                }
            }
            food1Time = System.currentTimeMillis();
        } else if (color.equals("green")) {
            xFood2 = food.getX();
            yFood2 = food.getY();
            if ((xFood2 == xFood1) && (yFood2 == yFood1)) {
                makeFood("green");
            } else {
                for (int i = 0; i < sLength; i++) {
                    if ((xFood2 == xCoord.get(i)) && (yFood2 == yCoord.get(i))) {
                        makeFood("green");
                        return;
                    }
                }
            }
            food2Time = System.currentTimeMillis();
        }
    }

    // If the user selects to play again, reset all major stats and create a new
    // snake of length 3
    public static void gameDispose() {
        xCoord.clear();
        yCoord.clear();
        score = 0;
        moves = 0;
        xHead = 0;
        yHead = 0;
        sLength = THREE;
        xHead = -(2 * MOVE_LENGTH);
        xCoord.add(xHead);
        yCoord.add(0);
        xCoord.add(-MOVE_LENGTH);
        yCoord.add(0);
        xCoord.add(0);
        yCoord.add(0);
    }

}
