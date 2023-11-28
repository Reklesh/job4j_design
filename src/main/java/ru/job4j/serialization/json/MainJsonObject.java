package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainJsonObject {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject("{\"param\":\"AB\"}");
        JSONArray jsonArray = new JSONArray(new int[]{1, 2, 3});
        final Person person = new Person(true, 100.5, "ABC", new NestedObject("AB"),
                new int[]{1, 2, 3});
        JSONObject jsObject = new JSONObject();
        jsObject.put("param1", person.getParam1());
        jsObject.put("param2", person.getParam2());
        jsObject.put("param3", person.getParam3());
        jsObject.put("object", jsonObject);
        jsObject.put("array", jsonArray);
        System.out.println(jsObject);
        System.out.println(new JSONObject(person));
    }
}
