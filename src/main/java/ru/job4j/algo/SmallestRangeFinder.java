package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] smallestRange = null;
        int i = 0;
        int j = 1;
        int count = 0;
        int rangeStart = 0;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                count++;
                if (count == k - 1) {
                    smallestRange = new int[]{rangeStart, j};
                    break;
                }
            } else if (count > 0) {
                count = 0;
                rangeStart = j;
            }
            i = j;
            j++;
        }
        return smallestRange;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 3, 3, 5, 5, 7, 7, 9, 10, 11, 12, 13, 14, 15};
        int k = 7;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}