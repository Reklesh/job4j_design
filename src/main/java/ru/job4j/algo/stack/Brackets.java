package ru.job4j.algo.stack;

import java.util.Deque;
import java.util.LinkedList;

public class Brackets {

    public static boolean isValid(String str) {
        boolean isValid;
        if (str.length() == 1) {
            isValid = false;
        } else if (str.isEmpty()) {
            isValid = true;
        } else {
            Deque<String> deque = new LinkedList<>();
            for (int i = 0; i < str.length(); i++) {
                String window = str.substring(i, i + 1);
                String bracket = deque.peekLast();
                if (bracket != null && ((bracket.equals("(") && window.equals(")"))
                        || (bracket.equals("{") && window.equals("}"))
                        || (bracket.equals("[") && window.equals("]")))) {
                    deque.pollLast();
                } else {
                    deque.add(window);
                }
            }
            isValid = deque.isEmpty();
        }
        return isValid;
    }

    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        long memoryBefore = run.totalMemory() - run.freeMemory();
        String str = "{[({[({{[({[({{[({[({{[({[({{[({[({{[({[({{[({[]})]}})]})]}})]})]}})]})]}})]})]}})]})]}})]})]}";
        boolean result = isValid(str);
        long memoryAfter = run.totalMemory() - run.freeMemory();
        System.out.println("Использовано памяти: " + (memoryAfter - memoryBefore) + " байт");
        System.out.print("Строка содержит валидное сочетание скобок: " + result);
    }
}