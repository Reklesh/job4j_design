package ru.job4j.ood.srp;

public class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void createUserAndPrintDetails() {
        User user = new User("Иван", 25);
        System.out.println("Пользователь создан: " + user.getName() + ", " + user.getAge());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

