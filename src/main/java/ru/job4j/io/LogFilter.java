package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = Collections.emptyList();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines()
                    .filter(s -> s.contains(" 404 "))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}