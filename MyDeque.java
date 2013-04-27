/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 23, ringbuffer
 */
import java.util.Scanner;

public class MyDeque<T> implements Deque<T> {
    // create the ring buffer as an array of type T
    private final T[] deque;
    // declare instance members to keep track of size, as well as the front and
    // the end of the ring buffer
    private int firstItem;
    private int lastItem;
    private final int size;

    @SuppressWarnings("unchecked")
    public MyDeque(final int maxSize) {
        size = maxSize;
        deque = (T[]) new Object[maxSize];
        firstItem = 0;
        lastItem = 0;
    }

    @Override
    // Test if buffer is empty
    public final boolean isEmpty() {
        return firstItem == lastItem;
    }

    @Override
    public final void addFirst(final T item) throws BufferFullException {
        if ((lastItem - firstItem) == deque.length) {
            throw new BufferFullException();
        }

        final int start = firstItem % size;
        for (int i = lastItem; i > firstItem; i--) {
            deque[i % size] = deque[(i - 1) % size];
        }
        deque[start] = item;
        lastItem++;
        System.out.println("addfirst " + item.toString());
    }

    @Override
    public final void addLast(final T item) throws BufferFullException {
        if ((lastItem - firstItem) == deque.length) {
            throw new BufferFullException();
        }

        final int end = lastItem % size;
        deque[end] = item;
        lastItem++;
        System.out.println("addlast " + item.toString());
    }

    @Override
    public final T removeLast() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }

        final int end = (lastItem - 1) % size;
        final T item = deque[end];
        deque[end] = null;
        lastItem--;
        System.out.println("removelast " + item.toString());
        return item;
    }

    @Override
    public final T removeFirst() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }

        final int start = firstItem % size;
        final T item = deque[start];
        deque[start] = null;
        firstItem++;
        System.out.println("removeFirst " + item.toString());
        return item;
    }

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);

        final MyDeque<Integer> deque = new MyDeque<Integer>(
                Integer.parseInt(args[0]));
        while (stdin.hasNext()) {
            final String command = stdin.next();

            try {
                if (command.equals("addfirst")) {
                    deque.addFirst(Integer.parseInt(stdin.next()));
                } else if (command.equals("addlast")) {
                    deque.addLast(Integer.parseInt(stdin.next()));
                } else if (command.equals("removefirst")) {
                    deque.removeFirst();
                } else if (command.equals("removelast")) {
                    deque.removeLast();
                } else {
                    continue;
                }

            } catch (final BufferFullException e) {
                System.out.println("Deque is full");
            } catch (final BufferEmptyException e) {
                System.out.println("Deque is empty");
            }
        }

        stdin.close();
    }

}
