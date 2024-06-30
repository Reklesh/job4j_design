package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {

    private final List<Food> listTrash = new ArrayList<>();

    @Override
    protected void conditionForAdd(long periodValidity, long periodValidityPast, Food food) {
        if (periodValidityPast == periodValidity || periodValidityPast > periodValidity) {
            listTrash.add(food);
        }
    }

    @Override
    public List<Food> getList() {
        return List.copyOf(listTrash);
    }
}
