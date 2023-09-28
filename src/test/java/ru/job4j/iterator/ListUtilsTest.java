package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3)
                .containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> filter = (integer) -> integer % 2 != 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).isEmpty();
    }

    @Test
    void whenReplaceIf() {
        Predicate<Integer> filter = (integer) -> integer % 2 != 0;
        Integer value = 6;
        ListUtils.replaceIf(input, filter, value);
        assertThat(input).isNotEmpty()
                .hasSize(2)
                .containsExactly(6, 6);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> elements = List.of(9, 3, 6, 1, 8);
        ListUtils.removeAll(input, elements);
        assertThat(input).isNotEmpty()
                .hasSize(3)
                .containsExactly(2, 4, 5);
    }

    @Test
    void whenNotRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> elements = List.of(7, 8, 9, 10);
        ListUtils.removeAll(input, elements);
        assertThat(input).isNotEmpty()
                .hasSize(6)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }
}