package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            return reader.lines()
                    .map(String::trim)
                    .map(s -> s.split(";", 2))
                    .filter(this::validate)
                    .map(e -> new User(e[0], e[1].substring(0, e[1].length() - 1)))
                    .toList();
        }
    }

    private boolean validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Неверный формат данных");
        }
        if (args[0] == null && args[1] == null) {
            throw new IllegalArgumentException("Отсутствует имя и почта спамера");
        }
        if (args[0] == null) {
            throw new IllegalArgumentException("Отсутствует имя спамера");
        }
        if (args[1] == null) {
            throw new IllegalArgumentException("Отсутствует почта спамера");
        }
        return true;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}