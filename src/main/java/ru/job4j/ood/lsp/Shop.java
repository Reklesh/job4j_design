package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {

    private final List<Food> listShop = new ArrayList<>();

    @Override
    protected void conditionForAdd(long periodValidity, long periodValidityPast, Food food) {
        if (periodValidityPast > periodValidity / 4 && periodValidityPast < (periodValidity / 4) * 3) {
            listShop.add(food);
        }
        if (periodValidityPast > (periodValidity / 4) * 3 && periodValidityPast < periodValidity) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount());
            listShop.add(food);
        }
    }

    @Override
    public List<Food> getList() {
        return List.copyOf(listShop);
    }
}
