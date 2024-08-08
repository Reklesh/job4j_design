package ru.job4j.algo.hash;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        String result = "";
        if (str.equals(result)) {
            result = str;
        } else {
            Map<Integer, String> map = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                String window = str.substring(i, i + 1);
                if (!result.contains(window)) {
                    result = result.concat(window);
                    if (i == str.length() - 1) {
                        map.putIfAbsent(result.length(), result);
                    }
                } else {
                    map.putIfAbsent(result.length(), result);
                    i = str.lastIndexOf(window, i - 1);
                    result = "";
                }
            }
            result = map.get(Collections.max(map.keySet()));
        }
        return result;
    }
}