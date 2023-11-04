package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        BiFunction<List<Path>, List<Path>, List<Path>> function
                = (oldValue, newValue) -> Stream.concat(oldValue.stream(), newValue.stream()).toList();
        map.merge(new FileProperty(file.toFile().length(), file.toFile().getName()),
                List.of(file.toAbsolutePath()), function);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getMap() {
        return map;
    }
}
