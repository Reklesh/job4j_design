package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class ParkingStorage implements Storage {

    private final List<Car> carList = new ArrayList<>();
    private final List<Car> truckList = new ArrayList<>();

    @Override
    public void addToCarList(Car car) {
    }

    @Override
    public void addToTruckList(Car car) {
    }

    @Override
    public int getCar() {
        return 0;
    }

    @Override
    public int getTruck() {
        return 0;
    }
}
