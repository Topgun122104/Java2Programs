/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 17, snake
 */
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

// Creates a new game of snake
public class Snake {
    // scaled down snake picture width
    private static final int PIC_WIDTH = 200;
    // Scaled down snake picture length
    private static final int PIC_LENGTH = 200;
    private static final int EASY_SPEED = 150;
    private static final int MEDIUM_SPEED = 100;
    private static final int HARD_SPEED = 50;
    // The main menu will last 4 seconds
    private static final int MENU_DELAY = 4000;
    private static final int AUTHOR_HEIGHT = -350;
    private static final int PIC_HEIGHT = -150;
    private static final int SNAKE_HEIGHT = 50;
    private static final int TO_HEIGHT = 150;
    private static final int WELCOME_HEIGHT = 250;
    private static final int FONT_SIZE = 60;
    // Height of main menu screen
    private static final int WIDTH = 640;
    // Width of main menu screen
    private static final int HEIGHT = 720;

    public static void main(final String[] args) {
        mainMenu();
        // Create a new game and pass in the selected difficulty
        snakeRunner(difficulty());
    }

    public static void snakeRunner(final int difficulty) {
        boolean inGame = false;
        String button = "";
        final Gameboard gb = new Gameboard(difficulty);

        while (gb.onCreate(inGame)) {
            gb.currentDir(button);

            if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && !button.equals("down")) {
                inGame = true;
                button = "up";
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)
                    && !button.equals("up")) {
                inGame = true;
                button = "down";
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)
                    && !button.equals("right")) {
                inGame = true;
                button = "left";
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)
                    && !button.equals("left")) {
                inGame = true;
                button = "right";
            }

        }
        // Prompt the user if they would like to play again after either losing
        // or winning
        final int answer = JOptionPane.showConfirmDialog(null,
                "Would you like to play again?", "Choose one",
                JOptionPane.YES_NO_OPTION);
        if (answer == 0) {
            // If the user presses a key after the game has ended, a glitch
            // occurs where the next game immediately starts and the snake
            // crashes. This tests for the button press and ends the game if
            // true.
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)
                    || StdDraw.isKeyPressed(KeyEvent.VK_DOWN)
                    || StdDraw.isKeyPressed(KeyEvent.VK_LEFT)
                    || StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                // glitch error message
                JOptionPane.showMessageDialog(null,
                        "You must restart the game to play again",
                        "You pressed an arrow after game over",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                // reset stats and begin a new game
                Gameboard.gameDispose();
                snakeRunner(difficulty());
            }
        } else {
            System.exit(0);
        }
    }

    // Pretty awesome main menu
    public static void mainMenu() {
        final int xScale = 320;
        final int yScale = 360;
        StdDraw.setCanvasSize(HEIGHT, WIDTH);
        StdDraw.setXscale(-xScale, xScale);
        StdDraw.setYscale(-yScale, yScale);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        final String welcome1 = String.format("Welcome");
        StdDraw.text(0, WELCOME_HEIGHT, welcome1);
        final String welcome2 = String.format("To");
        StdDraw.text(0, TO_HEIGHT, welcome2);
        final String welcome3 = String.format("Snake!!");
        StdDraw.text(0, SNAKE_HEIGHT, welcome3);
        StdDraw.picture(0, PIC_HEIGHT, "SnakeHead.jpg", PIC_LENGTH, PIC_WIDTH);
        StdDraw.setFont();
        final String author = String.format("By: Kenneth Truex");
        StdDraw.text(0, AUTHOR_HEIGHT, author);
        StdDraw.show(MENU_DELAY);
    }

    // Three difficulty settings that the user may select from
    public static int difficulty() {
        final Object[] options = {"Easy", "Medium", "Hard"};
        final String answer = (String) JOptionPane.showInputDialog(null,
                "Select a difficulty", "Before we begin...",
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        // If the user presses the cancel button, catch the otherwise null
        // pointer exception and end the game
        if (answer == null) {
            System.exit(0);
        } else if (answer.equals("Hard")) {
            return HARD_SPEED;
        } else if (answer.equals("Medium")) {
            return MEDIUM_SPEED;
        } else if (answer.equals("Easy")) {
            return EASY_SPEED;
        }
        return 0;
    }
}
