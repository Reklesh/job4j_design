package ru.job4j.algo.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        boolean isAdd;
        Optional<Node<T>> optional = findByKey(root, parent);
        if (optional.isEmpty() || findByKey(root, child).isPresent()) {
            isAdd = false;
        } else {
            isAdd = optional.get().getChildren().add(new Node<>(child));
        }
        return isAdd;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Queue is empty");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        Node<T> node = null;
        while (!stack.isEmpty()) {
            Node<T> tNode = stack.pop();
            if (tNode.getValue().equals(key)) {
                node = tNode;
                break;
            }
            List<Node<T>> children = tNode.getChildren();
            if (!children.isEmpty()) {
                children.forEach(stack::push);
            }
        }
        return Optional.ofNullable(node);
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Queue is empty");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        List<Node<T>> list = new ArrayList<>();
        stack.push(root);
        Node<T> node = null;
        while (!stack.isEmpty()) {
            Node<T> tNode = stack.pop();
            list.add(tNode);
            if (tNode.getValue().equals(key)) {
                node = tNode;
                if (list.size() > 1) {
                    for (Node<T> tNode1 : list) {
                        List<Node<T>> children = tNode1.getChildren();
                        if (children.remove(node)) {
                            break;
                        }
                    }
                }
                break;
            }
            List<Node<T>> children = tNode.getChildren();
            if (!children.isEmpty()) {
                children.forEach(stack::push);
            }
        }
        return Optional.ofNullable(node);
    }
}
