package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class AbstractStore implements Store {

    @Override
    public final void add(Food food) {
        LocalDate createDate = food.getCreateDate();
        LocalDate expiryDate = food.getExpiryDate();
        LocalDate currentDate = LocalDate.now();
        long periodValidity = ChronoUnit.DAYS.between(createDate, expiryDate);
        long periodValidityPast = ChronoUnit.DAYS.between(createDate, currentDate);
        conditionForAdd(periodValidity, periodValidityPast, food);
    }

    protected abstract void conditionForAdd(long periodValidity, long periodValidityPast, Food food);
}