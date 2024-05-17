package com.akshayram.javafunctional;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class JavaStreamsAPI {

    public static void main(String[] args) {
        // IntStream

        System.out.println("Method 1");
        IntStream.range(1, 10).forEach(System.out::println);

        System.out.println("Method 2");
        System.out.println("Using rangeClosed");
        // rangeClosed includes the number
        IntStream.rangeClosed(1, 10).forEach(System.out::println);

        System.out.println("First 100");
        IntStream.rangeClosed(1, 100)
                .forEach(
                        n -> System.out.print(n + " "));

        IntStream.rangeClosed(1, 100).forEach(n -> {
            if (n % 10 == 0) {
                System.out.println(n + " ");
            } else {
                System.out.print(n + " ");
            }
        });  //.forEach(n -> { System.out.print(n + " "); });
        System.out.println("\nLast :");
        IntStream.rangeClosed(1, 100).filter(num -> num % 10 == 0).forEach(System.out::println);

        Consumer<Integer> action =
                i -> {
                    if (i % 10 == 0) {
                        System.out.println(i + " ");
                    } else {
                        System.out.print(i + " ");
                    }
                };

        // IntStream.rangeClosed(1, 100).forEach(action);

        //      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //      List<Integer> evenNumbers = numbers.stream()
        //              .filter(n -> n % 2 == 0)
        //              .collect(Collectors.toList());//Transforming Elements with `map()`
        //
        //      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        //      List<Integer> nameLengths = names.stream()
        //              .map(String::length)
        //              .collect(Collectors.toList());
        //
        //      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        //      names.stream()
        //              .forEach(System.out::println);
        //
        //      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //      int sum = numbers.stream()
        //              .reduce(0, (a, b) -> a + b);
        //
        //      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //      int sum = numbers.stream()
        //              .reduce(0, (a, b) -> a + b);
        //
        //      List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3);
        //      List<Integer> distinctNumbers = numbers.stream()
        //              .distinct()
        //              .collect(Collectors.toList());
        //
        //      List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 7);
        //      List<Integer> sortedNumbers = numbers.stream()
        //              .sorted()
        //              .collect(Collectors.toList());
        //
        //      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //      List<Integer> limitedNumbers = numbers.stream()
        //              .limit(5)
        //              .collect(Collectors.toList());
        //      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //      List<Integer> skippedNumbers = numbers.stream()
        //              .skip(5)
        //              .collect(Collectors.toList());
        //
        //      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        //      boolean containsA = names.stream()
        //              .anyMatch(name -> name.contains("A"));
        //      boolean allEven = numbers.stream()
        //              .allMatch(n -> n % 2 == 0);
        //      boolean noneContainZ = names.stream()
        //              .noneMatch(name -> name.contains("Z"));
        //
        //      List<List<Integer>> nestedList = Arrays.asList(
        //              Arrays.asList(1, 2, 3),
        //              Arrays.asList(4, 5, 6),
        //              Arrays.asList(7, 8, 9)
        //      );
        //
        //      // Nested list into a single list
        //      Stream<Integer> flattenedStream = nestedList.stream()
        //              .flatMap(List::stream);
        //
        //      flattenedStream.forEach(System.out::println);
        //
        //      List<String> words = Arrays.asList("Hello", "World", "Java", "Streams");
        //
        //      Stream<String> characters = words.stream()
        //              .flatMap(word -> Arrays.stream(word.split("")))
        //              .distinct();
        //
        //      characters.forEach(System.out::println);
    }
}
