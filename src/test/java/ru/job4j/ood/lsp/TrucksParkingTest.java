package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class TrucksParkingTest {

    @Test
    void whenAddingTwoTrucksToParkingThenTwoTrucksAreOnParking() {
        Parking parkingCar = new TrucksParking();
        Storage storage = new ParkingStorage();
        Car car = new Car(2);
        parkingCar.conditionForAdd(car, 3, 2, storage);
        parkingCar.conditionForAdd(car, 3, 2, storage);
        assertThat(storage.getTruck()).isEqualTo(2);
    }

    @Test
    void whenNumberOfTrucksExceedsCapacityOfTruckParkingThenTrucksParkedOnCarParkingLots() {
        Parking parkingCar = new TrucksParking();
        Storage storage = new ParkingStorage();
        Car car = new Car(2);
        parkingCar.conditionForAdd(car, 3, 1, storage);
        parkingCar.conditionForAdd(car, 3, 1, storage);
        assertThat(storage.getCar()).isEqualTo(2);
    }

    @Test
    void whenAddingMoreTrucksThanParkingSpacesThenThrowsException() {
        Parking parkingCar = new TrucksParking();
        Storage storage = new ParkingStorage();
        Car car = new Car(4);
        parkingCar.conditionForAdd(car, 3, 1, storage);
        assertThatThrownBy(
                () -> parkingCar.conditionForAdd(car, 3, 1, storage))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}