package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    private void grow() {
        if (container.length == 0) {
            container = (T[]) new Object[1];
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public T set(int index, T newValue) {
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        size = size - 1;
        if (size > index) {
            System.arraycopy(container, index + 1, container, index, size - index);
        }
        container[size] = null;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int point;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
