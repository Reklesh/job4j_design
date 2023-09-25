package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        if (head != null) {
            Node<E> succ = head;
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> succ = head;
        for (int i = 0; i < index; i++) {
            succ = succ.next;
        }
        return succ.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<E> succ = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return succ != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = succ.item;
                succ = succ.next;
                return element;
            }
        };
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
