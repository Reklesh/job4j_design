package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ControlQualityTest {

    @Test
    void whenFoodAddToWarehouse() {
        Store store = new Warehouse();
        List<Store> list = new ArrayList<>(
                List.of(new Shop(), new Trash(), store));
        Food food = new Food(
                "Творог",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 8, 20),
                100D,
                0.2D
        );
        ControlQuality controlQuality = new ControlQuality(list);
        controlQuality.distribution(food);
        assertThat(store.getList().get(0).getName()).isEqualTo("Творог");
    }

    @Test
    void whenFoodAddToShop() {
        Store store = new Shop();
        List<Store> list = new ArrayList<>(
                List.of(new Trash(), store, new Warehouse()));
        Food food = new Food(
                "Сметана",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 7, 10),
                150D,
                0.2D
        );
        ControlQuality controlQuality = new ControlQuality(list);
        controlQuality.distribution(food);
        assertThat(store.getList().get(0).getName()).isEqualTo("Сметана");
    }

    @Test
    void whenFoodAddToShopWithDiscount() {
        Store store = new Shop();
        List<Store> list = new ArrayList<>(
                List.of(new Trash(), store, new Warehouse()));
        Food food = new Food(
                "Сметана",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 7, 3),
                150D,
                0.2D
        );
        ControlQuality controlQuality = new ControlQuality(list);
        controlQuality.distribution(food);
        assertThat(store.getList().get(0).getPrice()).isEqualTo(120D);
    }

    @Test
    void whenFoodAddToTrash() {
        Store store = new Trash();
        List<Store> list = new ArrayList<>(
                List.of(new Shop(), store, new Warehouse()));
        Food food = new Food(
                "Кефир",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 6, 30),
                100D,
                0.2D
        );
        ControlQuality controlQuality = new ControlQuality(list);
        controlQuality.distribution(food);
        assertThat(store.getList().get(0).getName()).isEqualTo("Кефир");
    }
}
