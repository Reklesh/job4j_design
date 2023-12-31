package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static boolean validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Указаны не все параметры поиска");
        }
        if (!Files.exists(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Указанный путь не существует");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Указанный путь не является каталогом");
        }
        if (!args[1].startsWith(".") && args[1].length() <= 1) {
            throw new IllegalArgumentException("Указано несуществующее расширение файла");
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        if (validate(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile()
                    .getName()
                    .endsWith(args[1]))
                    .forEach(System.out::println);
        }
    }
}