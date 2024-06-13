package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void testFizzBuzz() {
        assertThat(Fool.checkAnswer(15)).isEqualTo("FizzBuzz");
    }
    @Test
    void testFizz() {
        assertThat(Fool.checkAnswer(6)).isEqualTo("Fizz");
    }
    @Test
    void testBuzz() {
        assertThat(Fool.checkAnswer(10)).isEqualTo("Buzz");
    }
    @Test
    void testStartAt() {
        assertThat(Fool.checkAnswer(11)).isEqualTo("11");
    }
}