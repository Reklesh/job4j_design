package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Event implements Comparable<Event> {
    int time;
    boolean isStart;

    Event(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(Event other) {
        if (this.time == other.time) {
            return this.isStart ? -1 : 1;
        }
        return Integer.compare(this.time, other.time);
    }
}

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }
        if (intervals.size() == 1) {
            return new int[]{intervals.get(0).start, intervals.get(0).end};
        }
        List<Event> eventList = new ArrayList<>();
        intervals.forEach(interval -> {
            eventList.add(new Event(interval.start, true));
            eventList.add(new Event(interval.end, false));
        });
        Collections.sort(eventList);
        int currentOverlap = 0;
        int maxOverlap = 0;
        int startInterval = 0;
        int endInterval = 0;
        for (Event event : eventList) {
            if (event.isStart) {
                currentOverlap++;
                if (currentOverlap > maxOverlap) {
                    maxOverlap = currentOverlap;
                    startInterval = event.time;
                }
            } else {
                if (currentOverlap == maxOverlap) {
                    endInterval = event.time;
                }
                currentOverlap--;
            }
        }
        return new int[]{startInterval, endInterval};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));
        int[] result = findMaxOverlapInterval(intervals);
        System.out.println(
                "Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}