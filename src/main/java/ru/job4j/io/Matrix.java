package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {

    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                array[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[][] rst = multiple(5);
        try (FileOutputStream out = new FileOutputStream("data/Matrix.txt")) {
            for (int[] ints : rst) {
                for (int anInt : ints) {
                    out.write(Integer.toString(anInt).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
