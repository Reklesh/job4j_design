package ru.job4j.ood.ocp;

public class Printer {
    public void print(String text) {
        ConsolePrinter printer = new ConsolePrinter();
        printer.printText(text);
    }
}
