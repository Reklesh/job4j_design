package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineHRTest {

    @Test
    public void checkGenerateHR() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Nik", now, now, 300));
        store.add(new Employee("Petr", now, now, 200));
        Report engine = new ReportEngineHR(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Nik ")
                .append(300D)
                .append(System.lineSeparator())
                .append("Petr ")
                .append(200D)
                .append(System.lineSeparator())
                .append("Ivan ")
                .append(100D)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}