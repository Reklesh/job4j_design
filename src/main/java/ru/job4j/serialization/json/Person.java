package ru.job4j.serialization.json;

import java.util.Arrays;

public class Person {
    private final boolean param1;
    private final double param2;
    private final String param3;
    private final NestedObject object;
    private final int[] array;

    public Person(boolean param1, double param2, String param3, NestedObject object, int[] array) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.object = object;
        this.array = array;
    }

    @Override
    public String toString() {
        return "Person{"
                + "param1=" + param1
                + ", param2=" + param2
                + ", param3=" + param3
                + ", object=" + object
                + ", array=" + Arrays.toString(array)
                + '}';
    }
}