package ru.job4j.serialization.json;

public class NestedObject {
    private final String param;

    public NestedObject(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "NestedObject{"
                + "param='" + param + '\''
                + '}';
    }
}