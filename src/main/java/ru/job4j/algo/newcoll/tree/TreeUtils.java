package ru.job4j.algo.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Queue is empty");
        }
        int count = 0;
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            List<Node<T>> children = queue.poll().getChildren();
            if (!children.isEmpty()) {
                children.forEach(queue::push);
            }
            count++;
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Queue is empty");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> list = new ArrayList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            List<Node<T>> children = node.getChildren();
            if (!children.isEmpty()) {
                children.forEach(queue::push);
            }
            list.add(node.getValue());
        }
        return list;
    }
}
