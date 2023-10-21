package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        Map<Integer, String> map = new HashMap<>();
        for (User user1 : previous) {
            map.put(user1.getId(), user1.getName());
        }
        for (User user2 : current) {
            if (!map.containsKey(user2.getId())) {
                added++;
            }
            if (map.containsKey(user2.getId()) && !map.containsValue(user2.getName())) {
                changed++;
            }
            map.remove(user2.getId());
        }
        deleted = map.size();
        return new Info(added, changed, deleted);
    }
}