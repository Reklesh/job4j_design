package ru.job4j.ood.lsp;

import java.util.List;

public class ParkingService {

    private final List<Parking> list;
    private final int carParkingSpace;
    private final int truckParkingSpace;
    private final Storage storage;

    public ParkingService(List<Parking> list, int carParkingSpace, int truckParkingSpace, Storage storage) {
        this.list = list;
        this.carParkingSpace = carParkingSpace;
        this.truckParkingSpace = truckParkingSpace;
        this.storage = storage;
    }

    public void distribution(Car car) {
        for (Parking parking : list) {
            parking.conditionForAdd(car, carParkingSpace, truckParkingSpace, storage);
        }
    }
}