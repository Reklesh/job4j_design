package ru.job4j.collection;

public class SimpleStack<T> {

    private final ForwardLinked<T> linked = new ForwardLinked<>();
    private int size = 0;

    public T pop() {
        T element = linked.deleteFirst();
        size--;
        return element;
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}