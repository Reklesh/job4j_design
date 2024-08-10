package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalsTest {

    @Test
    public void whenIntervalsHaveOverlapsThenMergeOverlappingIntervals() {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = new int[][]{{1, 6}, {8, 10}, {15, 18}};
        int[][] result = Intervals.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsTouchAtEdgesThenMergeIntervals() {
        int[][] intervals = new int[][]{{1, 4}, {4, 5}};
        int[][] expected = new int[][]{{1, 5}};
        int[][] result = Intervals.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsAreNonOverlappingThenReturnSameIntervals() {
        int[][] intervals = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] result = Intervals.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsFullyOverlapThenMergeIntoOneInterval() {
        int[][] intervals = new int[][]{{1, 5}, {2, 6}, {3, 7}};
        int[][] expected = new int[][]{{1, 7}};
        int[][] result = Intervals.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsAreMixedThenMergeOverlappingAndKeepNonOverlapping() {
        int[][] intervals = new int[][]{{1, 4}, {0, 2}, {3, 5}};
        int[][] expected = new int[][]{{0, 5}};
        int[][] result = Intervals.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }
}