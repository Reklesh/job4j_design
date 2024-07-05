package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@Disabled
class TrashTest {

    @Test
    void checkAdd() {
        Store store = new Trash();
        Food food = new Food(
                "Кефир",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 6, 29),
                100D,
                0.2D
        );
        store.add(food);
        assertThat(store.getList().get(0).getName()).isEqualTo("Кефир");
    }
}
