package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        for (FileProperty file : visitor.getMap().keySet()) {
            List<Path> paths = visitor.getMap().get(file);
            if (paths.size() > 1) {
                System.out.printf("%s - %s%n", file.getName(), file.getSize());
                paths.forEach(value -> System.out.printf("    %s%n", value));
            }
        }
    }
}
