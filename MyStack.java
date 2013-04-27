/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 23, stack
 */
import java.util.Scanner;

public class MyStack<T> implements Stack<T> {
    private int size;
    private final int maxSize;
    private final T[] stack;

    @SuppressWarnings("unchecked")
    public MyStack(final int theMaxSize) {
        maxSize = theMaxSize;
        stack = (T[]) new Object[maxSize];
        size = 0;
    }

    @Override
    public final boolean isEmpty() {

        return size == 0;
    }

    @Override
    public final T pop() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }

        final T item = stack[size - 1];

        stack[size - 1] = null;
        size--;
        System.out.println("pop " + item.toString());
        return item;
    }

    @Override
    public final void push(final T item) throws BufferFullException {
        if (size == maxSize) {
            throw new BufferFullException();
        }
        stack[size] = item;
        size++;
        System.out.println("push " + item.toString());
    }

    public static void main(final String[] args) {
        final Scanner stdin = new Scanner(System.in);

        final MyStack<Integer> stack = new MyStack<Integer>(
                Integer.parseInt(args[0]));
        while (stdin.hasNext()) {
            final String command = stdin.next();

            try {
                if (command.equals("push")) {
                    stack.push(Integer.parseInt(stdin.next()));
                } else if (command.equals("pop")) {
                    stack.pop();
                } else {
                    continue;
                }

            } catch (final BufferFullException e) {
                System.out.println("Stack is full");
            } catch (final BufferEmptyException e) {
                System.out.println("Stack is empty");
            }
        }

        stdin.close();
    }

}
