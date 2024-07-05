package ru.job4j.ood.lsp;

import java.util.ArrayList;

import java.util.List;

public interface Parking {
    List<Car> CAR_LIST = new ArrayList<>();
    List<Car> TRUCK_LIST = new ArrayList<>();

    default void addToCarList(Car car) {
        CAR_LIST.add(car);
    }

    default void addToTruckList(Car car) {
        TRUCK_LIST.add(car);
    }

    default int getCar() {
        return CAR_LIST.size();
    }

    default int getTruck() {
        return TRUCK_LIST.size();
    }

    void conditionForAdd(Car car, int carParkingSpace, int truckParkingSpace);

}