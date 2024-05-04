package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner text = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(cachingDir + "/" + key), Charset.forName("WINDOWS-1251"))) {
            reader.lines().forEach(text::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}