package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
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
        var phrases = readPhrases();
        Scanner input = new Scanner(System.in);
        int answer;
        String name = input.nextLine();
        strings.add(name);
        while (!OUT.equals(name)) {
            if (STOP.equals(name)) {
                while (!CONTINUE.equals(name)) {
                    name = input.nextLine();
                    strings.add(name);
                }
            }
            answer = new Random().nextInt(phrases.size());
            System.out.println(phrases.get(answer));
            strings.add(phrases.get(answer));
            name = input.nextLine();
            strings.add(name);
        }
        saveLog(strings);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            list = in.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(this.path, Charset.forName("WINDOWS-1251")))) {
            log.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/console_chat.txt", "data/bot_answers.txt");
        cc.run();
    }
}
