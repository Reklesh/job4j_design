package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {

    private final List<Food> listWarehouse = new ArrayList<>();

    @Override
    protected void conditionForAdd(long periodValidity, long periodValidityPast, Food food) {
        if (periodValidityPast < periodValidity / 4) {
            listWarehouse.add(food);
        }
    }

    @Override
    public List<Food> getList() {
        return List.copyOf(listWarehouse);
    }
}
