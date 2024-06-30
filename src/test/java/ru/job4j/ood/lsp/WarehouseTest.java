package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    void checkAdd() {
        Store store = new Warehouse();
        Food food = new Food(
                "Творог",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 8, 20),
                100D,
                0.2D
        );
        store.add(food);
        assertThat(store.getList().get(0).getName()).isEqualTo("Творог");
    }
}