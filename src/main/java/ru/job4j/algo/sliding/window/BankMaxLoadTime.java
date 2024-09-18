package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        if (visitTimes.size() == 1) {
            var arr = visitTimes.get(0);
            return new int[]{arr[0], arr[1]};
        }
        List<Event> eventList = new ArrayList<>();
        visitTimes.forEach(interval -> {
            eventList.add(new Event(interval[0], EventType.ARRIVAL));
            eventList.add(new Event(interval[1], EventType.DEPARTURE));
        });
        Collections.sort(eventList);
        int currentOverlap = 0;
        int maxOverlap = 0;
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        for (Event event : eventList) {
            if (event.type.equals(EventType.ARRIVAL)) {
                currentOverlap++;
                if (currentOverlap > maxOverlap) {
                    maxOverlap = currentOverlap;
                    maxLoadStartTime = event.time;
                }
            } else {
                if (currentOverlap == maxOverlap) {
                    maxLoadEndTime = event.time;
                }
                currentOverlap--;
            }
        }
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}

