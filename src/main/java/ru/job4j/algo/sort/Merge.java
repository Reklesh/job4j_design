package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        var indexLeft = 0;
        var indexRight = 0;
        for (int i = 0; i < result.length; i++) {
            if (left[indexLeft] < right[indexRight]) {
                result[i] = left[indexLeft];
                indexLeft++;
                if (indexLeft == left.length) {
                    for (int j = i + 1; j < result.length; j++) {
                        result[j] = right[indexRight++];
                    }
                    break;
                }
            } else {
                result[i] = right[indexRight];
                indexRight++;
                if (indexRight == right.length) {
                    for (int j = i + 1; j < result.length; j++) {
                        result[j] = left[indexLeft++];
                    }
                    break;
                }
            }
        }
        return result;
    }
}
