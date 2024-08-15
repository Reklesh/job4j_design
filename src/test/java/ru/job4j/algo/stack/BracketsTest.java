package ru.job4j.algo.stack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BracketsTest {

    @Test
    void givenValidBracketsWhenIsValidThenTrue() {
        assertThat(Brackets.isValid("()")).isTrue();
        assertThat(Brackets.isValid("()[]{}")).isTrue();
        assertThat(Brackets.isValid("{[]}")).isTrue();
    }

    @Test
    void givenInvalidBracketsWhenIsValidThenFalse() {
        assertThat(Brackets.isValid("(]")).isFalse();
        assertThat(Brackets.isValid("([)]")).isFalse();
        assertThat(Brackets.isValid("]")).isFalse();
        assertThat(Brackets.isValid("{")).isFalse();
        assertThat(Brackets.isValid("")).isTrue();
    }
}