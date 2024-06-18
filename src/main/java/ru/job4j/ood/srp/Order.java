package ru.job4j.ood.srp;

import java.util.List;

public class Order {
    private List<Item> items;

    public void addItem(Item item) {
        items.add(item);
    }

    public void calculateTotal() {
        // вычисление общей суммы заказа
    }

    public void printOrderDetails() {
        // печать деталей заказа
    }
}

