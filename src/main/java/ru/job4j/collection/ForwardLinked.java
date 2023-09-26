package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (head != null) {
            Node<T> succ = head;
            for (int i = 0; i < size - 1; i++) {
                succ = succ.next;
            }
            succ.next = new Node<>(value, null);
        } else {
            head = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> succ = head;
        for (int i = 0; i < index; i++) {
            succ = succ.next;
        }
        return succ.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> next = head.next;
        T element = head.item;
        head.next = null;
        head.item = null;
        head = next;
        size--;
        modCount++;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<T> succ = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return succ != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = succ.item;
                succ = succ.next;
                return element;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
