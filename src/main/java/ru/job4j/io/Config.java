package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.putAll(read.lines()
                    .map(String::trim)
                    .filter(s -> s.length() != 0)
                    .filter(s -> !s.startsWith("#"))
                    .filter(this::validate)
                    .map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(
                            e -> e[0],
                            e -> e[1],
                            (first, second) -> first
                    )));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String str) {
        if (!str.contains("=")) {
            throw new IllegalArgumentException(String.format("this string: %s does not contain the symbol '='", str));
        }
        if (str.startsWith("=")) {
            throw new IllegalArgumentException(String.format("this string: %s does not contain a key", str));
        }
        if (str.indexOf("=") == str.length() - 1) {
            throw new IllegalArgumentException(String.format("this string: %s does not contain a value", str));
        }
        if (str.compareTo("=") == 0) {
            throw new IllegalArgumentException(String.format("this string: %s contains only the symbol '='", str));
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}

