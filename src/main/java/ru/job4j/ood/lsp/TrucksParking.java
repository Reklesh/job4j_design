package ru.job4j.ood.lsp;

import java.util.stream.IntStream;

public class TrucksParking implements Parking {

    @Override
    public void conditionForAdd(Car car, int carParkingSpace, int truckParkingSpace, Storage storage) {
        if (car.getSize() > 1) {
            if (truckParkingSpace > storage.getTruck()) {
                storage.addToTruckList(car);
            } else if (carParkingSpace - storage.getCar() >= car.getSize()) {
                IntStream.range(0, car.getSize()).forEach(i -> storage.addToCarList(car));
            } else {
                throw new IllegalArgumentException("Нет свободных мест для грузовых автомобилей");
            }
        }
    }
}