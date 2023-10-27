package com.akshayram.java8;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class JavaStreams {

  public static void main(String[] args) {
    // IntStream

    System.out.println("Method 1");
    IntStream.range(1, 10).forEach(System.out::println);

    System.out.println("Method 2");
    // range excludes the
    IntStream.range(1, 10).forEach(System.out::println);

    System.out.println("Using rangeClosed");
    // rangeClosed includes the number
    IntStream.rangeClosed(1, 10).forEach(System.out::println);

    System.out.println("First 100");
    IntStream.rangeClosed(1, 100)
        .forEach(
            n -> {
              System.out.print(n + " ");
            });

    // IntStream.rangeClosed(1, 100).forEach(n -> { if (n % 10) { System.out.println(n + " ") } else
    // { System.out.print(n + " ")} } );  //.forEach(n -> { System.out.print(n + " "); });
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

  }
}
