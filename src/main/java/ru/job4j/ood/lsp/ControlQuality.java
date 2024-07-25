package ru.job4j.ood.lsp;

import java.util.List;

public class ControlQuality {

    private final List<Store> list;

    public ControlQuality(List<Store> list) {
        this.list = list;
    }

    public void distribution(Food food) {
        for (Store store : list) {
            store.add(food);
        }
    }

    public void resort() {
        list.forEach(store -> store.getList().forEach(this::distribution));
    }
}
