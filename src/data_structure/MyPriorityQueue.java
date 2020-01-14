package data_structure;


import java.io.Serializable;
import java.util.Comparator;

public class MyPriorityQueue<T> implements Serializable {
    private Object[] queue;
    private int size = 0;
    private Comparator<? super T> comparator;
    private int MAX_SIZE = 9999;
    public MyPriorityQueue(Comparator<? super T> comparator, int capacity) {
        this.comparator = comparator;
        queue = new Object[capacity];
    }
    public MyPriorityQueue(Comparator<? super T> comparator) {
        this.comparator = comparator;
        queue = new Object[999];
    }

    public MyPriorityQueue(int capacity) {
        this(null, capacity);
    }

    public MyPriorityQueue() {
        this(null, 99);
    }

    public void push (T type) {
        if (size >= queue.length) {
            resize();
        }
        if (size == 0)
            queue[size++] = type;
        else
            siftUP(size++, type);
    }

    public T poll () {
        if (size == 0) {
            return null;
        }
        T result = (T)queue[0];
        T x = (T)queue[--size];
        queue[size] = null;
        if (size != 0) {
            siftDown(0, x);
        }
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek () {
        if (size == 0) {
            return null;
        }
        return (T)queue[size-1];
    }

    public void resize () {
        int newCap = size << 1;
        Object[] copy = new Object[newCap];
        System.arraycopy(queue, 0, copy, 0, queue.length);
        queue = copy;
    }

    private void siftUP (int k, T x) {
        if (comparator != null) {
            siftUpUsingCompartor(k, x);
        } else {
            siftUpUsingComparable(k, x);
        }
    }

    private void siftUpUsingCompartor (int k, T x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            if (comparator.compare(x, (T)queue[parent]) <= 0)
                break;
            queue[k] = queue[parent];
            k = parent;
        }
        queue[k] = x;
    }

    private void siftUpUsingComparable (int k, T x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            if (((Comparable<? super T>) x).compareTo((T) queue[parent])<= 0)
                break;
            queue[k] = queue[parent];
            k = parent;
        }
        queue[k] = x;
    }


    private void siftDown (int k, T x) {
        if (comparator != null) {
            siftDownUsingComparator(k, x);
        } else {
            siftDownUsingComparable(k, x);
        }
    }

    private void siftDownUsingComparator (int k, T x) {
        int half = (size >>> 1);
        while (k < half) {
            int child = (k << 1) +1;
            int right = child + 1;
            if (right < size &&
                    comparator.compare((T)queue[child], (T)queue[right]) < 0) {
                child = right;
            }
            if (comparator.compare(x, (T)queue[child]) >= 0) {
                break;
            }
            queue[k] = queue[child];
            k = child;
        }
        queue[k] = x;
    }

    private void siftDownUsingComparable (int k, T x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) +1;
            int right = child + 1;
            if (right < size &&
                    (((Comparable<? super T>)queue[child]).compareTo((T) queue[right])) < 0) {
                child = right;
            }
            if (((Comparable<? super T>) x).compareTo((T) queue[child]) >= 0) {
                break;
            }
            queue[k] = queue[child];
            k = child;
        }
        queue[k] = x;
    }

    public int getSize() {
        return size;
    }
}
