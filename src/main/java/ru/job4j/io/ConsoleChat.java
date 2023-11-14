package ru.job4j.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> strings = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int answer;
        String name = input.nextLine();
        strings.add(name);
        while (!name.equals(OUT)) {
            if (name.equals(STOP)) {
                while (!name.equals(CONTINUE)) {
                    name = input.nextLine();
                    strings.add(name);
                }
            }
            answer = new Random().nextInt(readPhrases().size());
            System.out.println(readPhrases().get(answer));
            strings.add(readPhrases().get(answer));
            name = input.nextLine();
            strings.add(name);
        }
        saveLog(strings);
    }

    private List<String> readPhrases() {
        List<String> lines = Collections.emptyList();
        Path path = Paths.get(botAnswers);
        try {
            lines = Files.readAllLines(path, Charset.forName("windows-1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void saveLog(List<String> log) {
        Path path = Paths.get(this.path);
        try {
            Files.write(path, log, Charset.forName("windows-1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/console_chat.txt", "data/bot_answers.txt");
        cc.run();
    }
}
