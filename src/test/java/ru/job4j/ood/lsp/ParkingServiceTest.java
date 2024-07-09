package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkingServiceTest {

    @Test
    void whenAddingCarToCarParkingLotThenCarIsParked() {
        List<Parking> list = List.of(new CarsParking(), new TrucksParking());
        Storage storage = new ParkingStorage();
        Car car = new Car(1);
        ParkingService service = new ParkingService(list, 5, 1, storage);
        service.distribution(car);
        assertThat(storage.getCar()).isEqualTo(1);
    }

    @Test
    void whenAddingCarButNoMoreSpaceThenThrowsException() {
        List<Parking> list = List.of(new CarsParking(), new TrucksParking());
        Storage storage = new ParkingStorage();
        Car car = new Car(1);
        ParkingService service = new ParkingService(list, 0, 1, storage);
        assertThatThrownBy(
                () -> service.distribution(car))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenAddingTruckToTruckParkingLotThenTruckIsParked() {
        List<Parking> list = List.of(new CarsParking(), new TrucksParking());
        Storage storage = new ParkingStorage();
        Car car = new Car(2);
        ParkingService service = new ParkingService(list, 1, 2, storage);
        service.distribution(car);
        assertThat(storage.getTruck()).isEqualTo(1);
    }

    @Test
    void whenAddingTruckToCarParkingLotWithNoSpaceInTruckParkingLotThenTruckIsStoredInCarParkingLot() {
        List<Parking> list = List.of(new CarsParking(), new TrucksParking());
        Storage storage = new ParkingStorage();
        Car car = new Car(2);
        ParkingService service = new ParkingService(list, 2, 0, storage);
        service.distribution(car);
        assertThat(storage.getCar()).isEqualTo(2);
    }

    @Test
    void whenAddingTruckToFullParkingLotsThenThrowException() {
        List<Parking> list = List.of(new CarsParking(), new TrucksParking());
        Storage storage = new ParkingStorage();
        Car car = new Car(2);
        ParkingService service = new ParkingService(list, 0, 0, storage);
        assertThatThrownBy(
                () -> service.distribution(car))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}