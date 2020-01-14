package data_structure;

import java.io.Serializable;
import java.util.Arrays;

public class MyQueue<T> implements Serializable {
    private int DEFAULT_SIZE = 30;
    private int capacity;//长度
    private Object[] elementData;//保存的元素
    private int front = 0;//队头
    private int rear = 0;//队尾

    public MyQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public MyQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    public MyQueue(int initSize) {
        elementData = new Object[initSize];
        this.capacity = initSize;
    }

    public MyQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    public int size() {
        return rear - front;
    }

    public void offer(T element) {
        ensureCapacity(rear + 1);
        elementData[rear++] = element;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void clear() {
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }

    public T poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }

        T oldValue = (T) elementData[front];
        elementData[front++] = null;
        return oldValue;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }
        return (T) elementData[front];
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}

