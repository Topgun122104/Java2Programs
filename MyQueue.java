/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 23, ringbuffer
 */
import java.util.Scanner;

public class MyQueue<T> implements Queue<T> {
    // Create instance memebers to keep track of the front and back ends of the
    // queue
    private int firstItem;
    private int lastItem;
    private final T[] queue;

    @SuppressWarnings("unchecked")
    public MyQueue(final int theMaxSize) {
        // Initialize the queue with an initial size taken from the command line
        // and set the front and back ends to 0
        queue = (T[]) new Object[theMaxSize];
        firstItem = 0;
        lastItem = 0;
    }

    @Override
    public final boolean isEmpty() {
        return firstItem == lastItem;
    }

    @Override
    public final T dequeue() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }

        final int startIndex = firstItem % queue.length;
        final T item = queue[startIndex];
        System.out.println("dequeue " + item.toString());
        queue[startIndex] = null;
        firstItem++;
        return item;
    }

    @Override
    public final void enqueue(final T item) throws BufferFullException {
        if ((lastItem - firstItem) == queue.length) {
            throw new BufferFullException();
        }

        final int end = lastItem % queue.length;
        queue[end] = item;
        lastItem++;
        System.out.println("enqueue " + item.toString());

    }

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);

        final MyQueue<Integer> queue = new MyQueue<Integer>(
                Integer.parseInt(args[0]));
        while (stdin.hasNext()) {
            final String command = stdin.next();

            try {
                if (command.equals("enqueue")) {
                    queue.enqueue(Integer.parseInt(stdin.next()));
                } else if (command.equals("dequeue")) {
                    queue.dequeue();
                } else {
                    continue;
                }

            } catch (final BufferFullException e) {
                System.out.println("Queue is full");
            } catch (final BufferEmptyException e) {
                System.out.println("Queue is empty");
            }
        }

        stdin.close();
    }

}
