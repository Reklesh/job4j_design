package ru.job4j.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Intervals {
    public static int[][] merge(int[][] intervals) {
        Comparator<int[]> comparator = Comparator.comparingInt((int[] a) -> a[0])
                .thenComparingInt((int[] a) -> a[1]);
        Arrays.sort(intervals, comparator);
        int i = 0;
        int j = 1;
        int count = 0;
        while (j < intervals.length) {
            if (intervals[i][0] == intervals[j][0]) {
                count++;
            } else if (intervals[i][1] >= intervals[j][0] && intervals[i][1] <= intervals[j][1]) {
                intervals[j][0] = intervals[i][0];
                count++;
            } else if (intervals[i][1] > intervals[j][1]) {
                intervals[j][0] = intervals[i][0];
                intervals[j][1] = intervals[i][1];
                count++;
            }
            i = j;
            j++;
        }
        return count > 0 ? Arrays.copyOfRange(intervals, count, intervals.length) : intervals;
    }

    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        long memoryBefore = run.totalMemory() - run.freeMemory();
        int[][] intervals = new int[][]{{1, 4}, {2, 6}, {2, 3}, {8, 10}, {15, 18}};
        int[][] result = merge(intervals);
        long memoryAfter = run.totalMemory() - run.freeMemory();
        System.out.println("Использовано памяти: " + (memoryAfter - memoryBefore) + " байт");
        Arrays.stream(result)
                .forEach(arr -> System.out.print(Arrays.toString(arr)));
    }
}
