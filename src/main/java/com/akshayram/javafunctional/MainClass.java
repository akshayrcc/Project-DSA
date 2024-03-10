package com.akshayram.javafunctional;

import java.util.*;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));


        // 1. Find all transactions in the year 2011 and sort them by value (small to high):
        List<Transaction> transactionsIn2011 = transactions.stream()
                .filter(transaction -> transaction.year() == 2011)
                .sorted(Comparator.comparingInt(Transaction::value))
                .toList();


        // 2. What are all the unique cities where the traders work?
        Set<String> uniqueCities = transactions.stream()
                .map(transaction -> transaction.trader().city())
                .distinct()
                .collect(Collectors.toSet());


        // 3. Find all traders from Cambridge and sort them by name:
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::trader)
                .filter(trader -> trader.city().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::name))
                .toList();


        // 4. Return a string of all traders' names sorted alphabetically:
        String allTraderNames = transactions.stream()
                .map(Transaction::trader)
                .map(Trader::name)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));


        // 5. Are any traders based in Milan?
        boolean anyMilanTraders = transactions.stream()
                .anyMatch(transaction -> transaction.trader().city().equals("Milan"));

        // 6. Print all transactions' values from the traders living in Cambridge:
        transactions.stream()
                .filter(transaction -> transaction.trader().city().equals("Cambridge"))
                .map(Transaction::value)
                .forEach(System.out::println);

        // 7. What's the highest value of all the transactions?
        OptionalInt highestValue = transactions.stream()
                .mapToInt(Transaction::value)
                .max();

        System.out.println("Highest value: " + highestValue.getAsInt());

        // 8. Find the transaction with the smallest value:
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(Comparator.comparingInt(Transaction::value));

        if (smallestTransaction.isPresent()) {
            System.out.println("Smallest transaction: " + smallestTransaction.get());
        } else {
            System.out.println("No transactions found.");
        }

    }
}
