package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {

    public static final Integer PUT_FILE = 1;
    public static final Integer GET_FILE = 2;

    public static final String SELECT = "Выберите меню:";
    public static final String FILE_NAME = "Введите имя файла";
    public static final String DIRECTORY_NAME = "Укажите кэшируемую директорию:";
    private static final String FILE_PUT_CACHE = "Содержимое файла загружено в кэш";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1 для загрузки содержимого файла в кэш.
                Введите 2 для получения содержимого файла из кэша.
                Введите любое другое число для выхода.
            """;

    private static String directory;

    private static void start(Scanner scanner, DirFileCache dirFileCache) {
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (PUT_FILE == userChoice) {
                System.out.println(FILE_NAME);
                String file = scanner.nextLine();
                validateFile(file, directory);
                dirFileCache.put(file, dirFileCache.load(file));
                System.out.println(FILE_PUT_CACHE);
            } else if (GET_FILE == userChoice) {
                System.out.println(FILE_NAME);
                String file = scanner.nextLine();
                validateFile(file, directory);
                System.out.println(dirFileCache.get(file));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static void validateFile(String file, String directory) {
        Path path = Paths.get(directory + "/" + file);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    String.format("В директории %s отсутствует указанный файл %s", directory, file));
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(
                    String.format("Указанное имя %s не является файлом", file));
        }
        if (!file.endsWith(".txt")) {
            throw new IllegalArgumentException("Указано неверное расширение файла");
        }
    }

    private static void validateDirectory(String directory) {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    String.format("Указанной директории %s не существует", directory));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(
                    String.format("Указанное имя %s не является директорией", directory));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIRECTORY_NAME);
        directory = scanner.nextLine();
        validateDirectory(directory);
        DirFileCache dirFileCache = new DirFileCache(directory);
        start(scanner, dirFileCache);
    }
}
