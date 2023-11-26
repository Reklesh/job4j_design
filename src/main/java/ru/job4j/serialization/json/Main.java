package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(true, 100.5, "ABC", new NestedObject("AB"),
                new int[]{1, 2, 3});
        System.out.println(person);
        final Gson gson = new GsonBuilder().create();
        String personJson = gson.toJson(person);
        System.out.println(personJson);
        final Person personBack = gson.fromJson(personJson, Person.class);
        System.out.println(personBack);
    }
}