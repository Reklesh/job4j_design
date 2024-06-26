package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public JsonReportEngine(Store store) {
        this.store = store;
        this.dateTimeParser = new ReportDateTimeParser();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Employee employee : store.findBy(filter)) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("name", employee.getName());
            map.put("hired", dateTimeParser.parse(employee.getHired()));
            map.put("fired", dateTimeParser.parse(employee.getFired()));
            map.put("salary", employee.getSalary());
            list.add(map);
        }
        return gson.toJson(list);
    }
}
