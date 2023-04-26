package AdvancedTopics_4.BuildingCustomSynchronizers_14.Examples;

public class BaseBoundedBuffer<V> {
    private final V[] buffer;
    private int tail;
    private int head;
    private int count;

    public BaseBoundedBuffer(int capacity) {
        this.buffer = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V value) {
        buffer[tail] = value;
        if (++tail == buffer.length) {
            tail = 0;
        }
        ++count;
    }

    public synchronized final boolean isFull() {
        return count == buffer.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }
}
