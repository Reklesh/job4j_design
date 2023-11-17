package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        boolean rev = true;
        int[] indexRow = new int[]{};
        StringJoiner lineToFile;
        try (var scanner = new Scanner(new FileReader(argsName.get("path")));
             PrintStream stream = new PrintStream(new FileOutputStream(argsName.get("out")))) {
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(argsName.get("delimiter"));
                if (rev) {
                    indexRow = filter(str, argsName);
                    rev = false;
                }
                lineToFile = new StringJoiner(argsName.get("delimiter"), "", System.lineSeparator());
                for (int i : indexRow) {
                    lineToFile.add(str[i]);
                }
                stream.print(lineToFile);
            }
        }
    }

    private static int[] filter(String[] array, ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        int[] ints = new int[filter.length];
        int i = 0;
        int index;
        for (String s : filter) {
            index = Arrays.asList(array).indexOf(s);
            ints[i++] = index;
        }
        return ints;
    }

    private static boolean validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не существует", argsName.get("path")));
        }
        if (!Files.isRegularFile(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не является файлом", argsName.get("path")));
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Указано неверное расширение исходного файла");
        }
        if (!Files.exists(Paths.get(argsName.get("out")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не существует", argsName.get("out")));
        }
        if (!Files.isRegularFile(Paths.get(argsName.get("out")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не является файлом", argsName.get("out")));
        }
        if (!argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Указано неверное расширение конечного файла");
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Указаны не все параметры поиска");
        }
        ArgsName argsName = ArgsName.of(args);
        if (validate(argsName)) {
            handle(argsName);
        }
    }
}
