package ru.job4j.algo.collection.binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(10)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly(10);
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(10)).isTrue();
        assertThat(tree.insert(11)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly(10, 11);
    }

    @Test
    void whenAddElementThenContainElementOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        assertThat(tree.contains(11)).isTrue();
        assertThat(tree.contains(13)).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{7, 2, 3, 4, 5, 1, 6}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{1, 2, 3, 4, 5, 6, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void checkRemove() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.inPostOrder()).hasSize(16);
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.inPostOrder()).hasSize(15)
                .containsExactly(1, 4, 2, 7, 9, 8, 6, 11, 13, 12, 15, 17, 16, 14, 10);
    }
}