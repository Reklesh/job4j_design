package ru.job4j.serialization.json;

public class NestedObject {
    private final String param;

    public NestedObject(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "NestedObject{"
                + "param='" + param + '\''
                + '}';
    }
}