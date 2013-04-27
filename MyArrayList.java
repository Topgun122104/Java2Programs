/*
 * Author: Kenneth Truex, ktruex2012@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2013
 * Project: Proj 18, myarraylist
 */
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class MyArrayList extends AbstractList<Integer> {
    private static final int FIFTEEN = 15;
    private static final int TWELVE = 12;
    private static final int SEVEN = 7;
    private static final int SIX = 6;
    private static final int FIVE = 5;
    private static final int TEST_SIZE = 19;
    private static final int FOUR = 4;
    private static final int THREE = 3;
    private static final int TEN = 10;
    private static final int NINE = 9;
    // The default capacity when one isn't specified
    private static final int DEFAULT_CAP = 10;
    // The actual representation of the ArratyList.
    private int[] arrayList;
    // The current size of the ArrayList
    private int size;
    // The current length of the integer array
    private int capacity;

    // Use constructor chaining to initialize the capacity to 10 when a capacity
    // isn't specified
    public MyArrayList() {
        this(DEFAULT_CAP);
    }

    // The main constructor which takes an initial capacity as an argument
    public MyArrayList(final int initialCapacity) {
        // initialize the array to have the capacity specified by the argument
        arrayList = new int[initialCapacity];
        // There are initially no elements in the array
        size = 0;
        capacity = initialCapacity;
    }

    // Always ensure that the array has enough capacity to hold the added
    // element
    private void ensureCapacity(final int minCapacity) {
        // If the array becomes full, double the size
        if (capacity <= (minCapacity + 1)) {
            final int newCap = capacity * 2;
            arrayList = Arrays.copyOf(arrayList, newCap);
            capacity = newCap;
        }
    }

    // Add the specified element to the next availbale index in the array.
    // Double the array is necessary
    @Override
    public final boolean add(final Integer element) {
        // First, check to see that the array can support another element
        ensureCapacity(size);
        // Add the element to the next available slot
        arrayList[size++] = element;
        return true;
    }

    @Override
    public final void add(final int index, final Integer v) {
        ensureCapacity(size);
        checkRange(index);
        for (int i = size; i >= index; i--) {
            arrayList[i + 1] = arrayList[i];
        }
        arrayList[index] = v;
        size++;

    }

    // Get an element based on index location
    @Override
    public final Integer get(final int index) {
        if (!checkRange(index)) {
            throw new IndexOutOfBoundsException();
        } else {
            return arrayList[index];
        }
    }

    // Set the element at the specified index to the specified element and then
    // return the initial element
    @Override
    public final Integer set(final int index, final Integer element) {
        if (!checkRange(index)) {
            throw new IndexOutOfBoundsException();
        } else {
            final int temp = arrayList[index];
            arrayList[index] = element;
            return temp;
        }
    }

    // Remove the element at the specified index
    @Override
    public final Integer remove(final int index) {
        // Verify that the specified index is valid
        if (!checkRange(index)) {
            throw new IndexOutOfBoundsException();
        } else {
            final int temp = arrayList[index];
            // Shift all elements after the removed elements down
            for (int i = index; i < (size - 1); i++) {
                arrayList[i] = arrayList[i + 1];
                arrayList[i + 1] = 0;
            }
            size--;
            return temp;

        }
    }

    // Locate and remove the specified element
    public final boolean remove(final Integer element) {
        final int index = linearSearch(element);
        if (index == -1) {
            return false;
        } else {
            for (int i = index; i < (size - 1); i++) {
                arrayList[i] = arrayList[i + 1];
                arrayList[i + 1] = 0;
            }
            size--;
            return true;

        }
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final void clear() {
        Arrays.fill(arrayList, 0);
        size = 0;
    }

    // Ensure specified index is valid
    private boolean checkRange(final int index) {
        if ((index > size) || (index < 0)) {
            return false;
        }
        return true;
    }

    // Search the array for the first occurrence of the specified element.
    // Return the index location
    private int linearSearch(final Integer element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element) {
                return i;
            }
        }
        return -1;
    }

    // Display the current integer array representation of the ArrayList
    @Override
    public final String toString() {
        final int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            temp[i] = arrayList[i];
        }
        return Arrays.toString(temp);
    }

    // Unit Test
    public static void main(final String[] args) {
        final List<Integer> myList = new MyArrayList();
        for (int i = TEST_SIZE; i >= 0; i--) {
            myList.add(i);
        }
        myList.add(FOUR, new Integer(NINE));
        myList.add(FIVE, new Integer(TEN));
        myList.add(SIX, new Integer(TWELVE));
        myList.add(SEVEN, new Integer(FIFTEEN));
        System.out.println(myList.toString());

        myList.remove(new Integer(NINE));
        myList.add(TEN);
        myList.add(THREE);
        System.out.println(myList.toString());
        myList.remove(1);
        System.out.println(myList.toString());
        System.out.println(myList.get(FOUR));
        myList.set(0, TEN);
        System.out.println(myList.toString());
        System.out.println(myList.size());
        myList.clear();
        System.out.println(myList.toString());

    }

}
