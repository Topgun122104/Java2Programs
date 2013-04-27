interface Stack<T> {
    boolean isEmpty();
    T pop() throws BufferEmptyException;
    void push (T item) throws BufferFullException;
}

interface Queue<T> {
    boolean isEmpty();
    T dequeue()  throws BufferEmptyException;
    void enqueue(T item) throws BufferFullException;
}

interface Deque<T> {
    boolean isEmpty();
    void addFirst(T item) throws BufferFullException;
    void addLast(T item) throws BufferFullException;
    T removeLast() throws BufferEmptyException;
    T removeFirst() throws BufferEmptyException;
}

// To raise this exception in case of full buffer, use this code:
//
//     throw new BufferEmptyException();
//
@SuppressWarnings("serial")
class BufferEmptyException extends Exception {
    
}
@SuppressWarnings("serial")
class BufferFullException extends Exception {
    
}