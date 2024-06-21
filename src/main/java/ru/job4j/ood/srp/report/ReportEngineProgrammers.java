package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngineProgrammers implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private String path;

    public ReportEngineProgrammers(Store store, DateTimeParser<Calendar> dateTimeParser, String path) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.path = path;
    }

    public ReportEngineProgrammers(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public void saveReport(Predicate<Employee> filter) {
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("Указано неверное расширение файла для отчета");
        }
        try (FileWriter writer = new FileWriter(path, Charset.forName("WINDOWS-1251"))) {
            writer.write(generate(filter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
