package com.example.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task04Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        Stream<String> words = reader.lines()
                .flatMap(line -> Arrays.stream(line.split("[^a-zA-Zа-яА-ЯёЁ0-9]+")))
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase);

        Map<String, Long> frequencyMap = words.collect(
                Collectors.groupingBy(word -> word, Collectors.counting())
        );

        Comparator<Map.Entry<String, Long>> comparator =
                Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey());

        frequencyMap.entrySet().stream()
                .sorted(comparator)
                .limit(10)
                .forEach(entry -> System.out.print(entry.getKey() + "\n"));

    }

}
