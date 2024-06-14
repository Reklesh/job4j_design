package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTemplateTest {
    @Test
    public void checkGetString() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev", "subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        Generator generator = new GeneratorTemplate();
        String rsl = generator.produce(template, map);
        assertThat(rsl).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    public void whenInTemplateKeysWhichNotInMapThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev", "subject", "you");
        String template = "I am a ${name}, Who are ${subject}?, What is the ${datetime}? ";
        Generator generator = new GeneratorTemplate();
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenInMapKeysWhichNotInTemplateThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev", "subject", "you", "Datetime", "14.06.2024");
        String template = "I am a ${name}, Who are ${subject}? ";
        Generator generator = new GeneratorTemplate();
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }
}