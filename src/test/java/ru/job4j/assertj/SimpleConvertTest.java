package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .contains("first", "second", "three")
                .containsExactly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("three", "first", "four", "second", "five")
                .containsAnyOf("one", "first", "two")
                .doesNotContain("one", "seven", "two")
                .startsWith("first", "second")
                .endsWith("four", "five")
                .containsSequence("three", "four");
        assertThat(list).first().isNotNull()
                .isEqualTo("first");
        assertThat(list).element(2).isNotNull()
                .isEqualTo("three");
        assertThat(list).last().isNotNull()
                .isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).isNotEmpty()
                .hasSize(5)
                .contains("first", "second", "three")
                .containsExactlyInAnyOrder("three", "first", "four", "second", "five")
                .containsAnyOf("one", "first", "two")
                .doesNotContain("one", "seven", "two");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three");
        assertThat(map).hasSize(3)
                .containsKeys("one", "two", "three")
                .containsValues(0, 1, 2)
                .doesNotContainKey("four")
                .doesNotContainValue(3)
                .containsEntry("three", 2);
    }
}