package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                .map(String::trim)
                .filter(this::validate)
                .map(str -> str.substring(1))
                .map(str -> str.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0],
                        e -> e[1],
                        (first, second) -> first
                )));
    }

    private boolean validate(String str) {
        if (str.indexOf("-") == 0 && str.indexOf("=") == 1) {
            throw new IllegalArgumentException(String.
                    format("Error: This argument '%s' does not contain a key", str));
        }
        if (str.indexOf("=") == str.length() - 1) {
            throw new IllegalArgumentException(String.
                    format("Error: This argument '%s' does not contain a value", str));
        }
        if (!str.contains("=")) {
            throw new IllegalArgumentException(String.
                    format("Error: This argument '%s' does not contain an equal sign", str));
        }
        if (!str.startsWith("-")) {
            throw new IllegalArgumentException(String.
                    format("Error: This argument '%s' does not start with a '-' character", str));
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}