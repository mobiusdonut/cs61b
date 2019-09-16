//package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T>, Iterable<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int capacity;
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull())
        {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        this.last += 1;
        if (this.last == capacity)
        {
            this.last = 0;
        }
        this.fillCount += 1;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty())
        {
            throw new RuntimeException("Ring buffer underflow");
        }
        T itemToReturn = rb[first];
        this.first += 1;
        if (this.first == capacity)
        {
            this.first = 0;
        }
        this.fillCount -= 1;
        return itemToReturn;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty())
        {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
    public boolean isEmpty() {
        return fillCount == 0;
    }
    public boolean isFull() {
        return fillCount == capacity;
    }

    private class pointer implements Iterator<T>
    {
        public int pos = 0;
        public boolean hasNext()
        {
            return pos != capacity();
        }
        public T next()
        {
            T current = rb[pos];
            pos += 1;
            return current;
        }
    }
    public Iterator<T> iterator()
    {
        return new pointer();
    }
}
