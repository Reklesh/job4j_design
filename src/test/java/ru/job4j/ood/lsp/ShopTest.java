package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ShopTest {

    @Test
    void checkAdd() {
        Store store = new Shop();
        Food food = new Food(
                "Сметана",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 7, 10),
                150D,
                0.2D
        );
        store.add(food);
        assertThat(store.getList().get(0).getName()).isEqualTo("Сметана");
    }

    @Test
    void checkAddWithDiscount() {
        Store store = new Shop();
        Food food = new Food(
                "Сметана",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 7, 3),
                150D,
                0.2D
        );
        store.add(food);
        assertThat(store.getList().get(0).getPrice()).isEqualTo(120D);
    }
}
