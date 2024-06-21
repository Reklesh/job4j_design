package ru.job4j.ood.srp.store;

import ru.job4j.ood.srp.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MemoryStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream()
                .filter(filter)
                .toList();
    }
}