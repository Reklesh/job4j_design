package ru.job4j.algo.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean containsKey(T key) {
        return Objects.nonNull(root) && Objects.nonNull(find(root, key));
    }

    private Node find(Node node, T key) {
        if (Objects.isNull(node) || key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key)) {
            root = put(root, key, value);
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = put(node.left, key, value);
            } else if (comparisonResult > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && containsKey(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node minimum(Node node) {
        return Objects.isNull(node.left) ? node : minimum(node.left);
    }

    private Node maximum(Node node) {
        return Objects.isNull(node.right) ? node : maximum(node.right);
    }

    public V get(T key) {
        Node node = null;
        if (Objects.nonNull(root) && Objects.nonNull(key)) {
            node = find(root, key);
        }
        return Objects.nonNull(node) ? node.value : null;
    }

    public Set<T> keySet() {
        Set<T> result = new TreeSet<>();
        Node node = root;
        return keySet(node, result);
    }

    private Set<T> keySet(Node localRoot, Set<T> set) {
        if (Objects.nonNull(localRoot)) {
            keySet(localRoot.left, set);
            set.add(localRoot.key);
            keySet(localRoot.right, set);
        }
        return set;
    }

    public Collection<V> values() {
        List<V> result = new ArrayList<>();
        Node node = root;
        return values(node, result);
    }

    private Collection<V> values(Node localRoot, List<V> list) {
        if (Objects.nonNull(localRoot)) {
            values(localRoot.left, list);
            list.add(localRoot.value);
            values(localRoot.right, list);
        }
        return list;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
