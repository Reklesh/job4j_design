package ru.job4j.ood.lsp;

import java.util.List;

public class ParkingService {
    private final List<Parking> list;
    private final int carParkingSpace;
    private final int truckParkingSpace;

    public ParkingService(List<Parking> list, int carParkingSpace, int truckParkingSpace) {
        this.list = list;
        this.carParkingSpace = carParkingSpace;
        this.truckParkingSpace = truckParkingSpace;
    }

    public void distribution(Car car) {
    }

}
