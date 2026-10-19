package com.example.task03;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) {

        findMinMax(
                Stream.of(2, 9, 5, 4, 8, 1, 3),
                Integer::compareTo,
                (min, max) ->
                        System.out.println("min: " + min + " / max: " + max)
        );

    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        if (stream == null || order == null || minMaxConsumer == null) {
            throw new NullPointerException();
        }

        Object[] result = stream.reduce(
                null,
                (acc, element) -> {
                    if (acc == null) {
                        return new Object[]{element, element};
                    }

                    T currentMin = (T) acc[0];
                    T currentMax = (T) acc[1];

                    T newMin = order.compare(element, currentMin) < 0 ? element : currentMin;
                    T newMax = order.compare(element, currentMax) > 0 ? element : currentMax;

                    return new Object[]{newMin, newMax};
                },
                (acc1, acc2) -> {

                    if (acc1 == null) return acc2;
                    if (acc2 == null) return acc1;
                    T min1 = (T) acc1[0];
                    T max1 = (T) acc1[1];
                    T min2 = (T) acc2[0];
                    T max2 = (T) acc2[1];
                    T finalMin = order.compare(min1, min2) < 0 ? min1 : min2;
                    T finalMax = order.compare(max1, max2) > 0 ? max1 : max2;
                    return new Object[]{finalMin, finalMax};
                }
        );

        if (result == null) {
            minMaxConsumer.accept(null, null);
        } else {
            minMaxConsumer.accept((T) result[0], (T) result[1]);
        }
    }
}
