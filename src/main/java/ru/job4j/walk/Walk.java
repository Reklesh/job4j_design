package ru.job4j.walk;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Walk {

    public void findFiles(List<Path> sources, File target) {
        try (PrintStream stream = new PrintStream(new FileOutputStream(target))) {
            sources.forEach(path -> stream.println(path.toFile().getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не существует", argsName.get("d")));
        }
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не является каталогом", argsName.get("d")));
        }
        if (!Files.exists(Paths.get(argsName.get("o")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не существует", argsName.get("o")));
        }
        if (!Files.isRegularFile(Paths.get(argsName.get("o")))) {
            throw new IllegalArgumentException(
                    String.format("Указанный путь %s не является файлом", argsName.get("o")));
        }
        if (!argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("Указан неверный формат файла");
        }
        if (argsName.get("t").compareTo("mask") != 0 && argsName.get("t").compareTo("regex") != 0
                && argsName.get("t").compareTo("name") != 0) {
            throw new IllegalArgumentException("Указан неверный тип поиска");
        }
    }

    private static Predicate<Path> condition(String pattern, String typeSearch) {
        String s = Objects.equals(typeSearch, "mask") ? "glob:" : "regex:";
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(s + pattern);
        return p -> matcher.matches(p.getFileName());
    }

    public static void main(String[] args) throws IOException {
        Walk walk = new Walk();
        if (args.length != 4) {
            throw new IllegalArgumentException("Указаны не все параметры поиска");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        Path start = Paths.get(argsName.get("d"));
        walk.findFiles(Search.search(start, condition(
                argsName.get("n"), argsName.get("t"))), new File(argsName.get("o")));
    }
}
