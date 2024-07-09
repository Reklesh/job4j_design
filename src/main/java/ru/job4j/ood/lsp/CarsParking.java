package ru.job4j.ood.lsp;

public class CarsParking implements Parking {

    @Override
    public void conditionForAdd(Car car, int carParkingSpace, int truckParkingSpace, Storage storage) {
        if (car.getSize() == 1) {
            if (carParkingSpace > storage.getCar()) {
                storage.addToCarList(car);
            } else {
                throw new IllegalArgumentException("Нет свободных мест для легковых автомобилей");
            }
        }
    }
}