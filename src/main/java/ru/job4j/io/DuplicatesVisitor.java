package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        map.computeIfAbsent(new FileProperty(file.toFile().length(), file.toFile().getName()),
                k -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getMap() {
        for (FileProperty file : map.keySet()) {
            List<Path> paths = map.get(file);
            if (paths.size() > 1) {
                System.out.printf("%s - %s%n", file.getName(), file.getSize());
                paths.forEach(value -> System.out.printf("    %s%n", value));
            }
        }
    }
}
