package org.ahivs.java.streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasics {
    private static void streamBasics() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        list.stream().map(i -> transform(i))
                .forEach(System.out::println);
    }

    private static int transform(int val) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return val * 2;
    }

    private static void streamParallel() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        list.parallelStream().map(i -> transform(i))
                .forEach(System.out::println);
    }

    private static void filter() {
        Set<String> visited = new HashSet<>();
        visited.add("a");
        List<String> list = Arrays.asList("a","b","c","d","e","f ");
        Stream<String> stream = list.parallelStream().filter(s -> visited.add(s));
        //stream.forEach(System.out::println);

        Stream<String> concat = Stream.concat(Stream.of("b"), stream);
        concat.forEach(System.out::println);

    }

    public static void main(String[] args) {
        filter();
    }
}
