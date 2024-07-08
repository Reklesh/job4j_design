package ru.job4j.ood.lsp;

public interface Storage {

    void addToCarList(Car car);

    void addToTruckList(Car car);

    int getCar();

    int getTruck();
}
