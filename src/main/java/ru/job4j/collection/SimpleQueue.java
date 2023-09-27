package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeOut == 0 && sizeIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeOut > 0) {
            sizeOut--;
        } else {
            for (int i = 0; i < sizeIn; i++) {
                out.push(in.pop());
                sizeOut++;
            }
            sizeOut--;
            sizeIn = 0;
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
