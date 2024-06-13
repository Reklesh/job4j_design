package ru.job4j.kiss.fool;

import java.util.Objects;
import java.util.Scanner;

public class Fool {

    public static String checkAnswer(int startAt) {
        var rsl = String.valueOf(startAt);
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            rsl = "FizzBuzz";
        } else if (startAt % 3 == 0) {
            rsl = "Fizz";
        } else if (startAt % 5 == 0) {
            rsl = "Buzz";
        }
        return rsl;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(checkAnswer(startAt++));
            var answer = input.nextLine();
            if (!Objects.equals(answer, checkAnswer(startAt))) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}