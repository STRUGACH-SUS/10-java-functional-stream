package com.example.task02;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task02Main {

    public static void main(String[] args) {

        cycleGrayCode(2)
                .limit(10)
                .forEach(System.out::println);


    }

    public static IntStream cycleGrayCode(int n) {
        if (n < 1 || n > 16) {
            throw new IllegalArgumentException("n должно быть от 1 до 16");
        }
        int max = 1 << n;
        return IntStream.iterate(0, i -> i + 1)
                .map(i -> i % max)
                .map(i -> i ^ (i >> 1));
    }

}
