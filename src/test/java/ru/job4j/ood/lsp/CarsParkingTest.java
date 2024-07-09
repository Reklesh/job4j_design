package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsParkingTest {

    @Test
    void whenAddingThreeCarsToParkingThenThreeCarsAreOnParking() {
        Parking parkingCar = new CarsParking();
        Storage storage = new ParkingStorage();
        Car car = new Car(1);
        parkingCar.conditionForAdd(car, 3, 1, storage);
        parkingCar.conditionForAdd(car, 3, 1, storage);
        parkingCar.conditionForAdd(car, 3, 1, storage);
        assertThat(storage.getCar()).isEqualTo(3);
    }

    @Test
    void whenAddingMoreCarsThanParkingSpacesAvailableThenExceptionIsThrown() {
        Parking parkingCar = new CarsParking();
        Storage storage = new ParkingStorage();
        Car car = new Car(1);
        parkingCar.conditionForAdd(car, 2, 1, storage);
        parkingCar.conditionForAdd(car, 2, 1, storage);
        assertThatThrownBy(
                () -> parkingCar.conditionForAdd(car, 2, 1, storage))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}