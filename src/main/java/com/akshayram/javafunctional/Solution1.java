package com.akshayram.javafunctional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution1 {

    private static final Map<String, Employee> map1 = new HashMap<>();
    private static final Map<String, Employee> map2 = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Ans" + justMethod(new int[]{1, -2, -3, 5}));

        Employee employee1 = new Employee(1L, "Henry");
        map1.put(employee1.getName(), employee1);
        Employee employee2 = new Employee(22L, "Annie");
        map1.put(employee2.getName(), employee2);
        Employee employee3 = new Employee(8L, "John");
        map1.put(employee3.getName(), employee3);

        Employee employee4 = new Employee(2L, "George");
        map2.put(employee4.getName(), employee4);
        Employee employee5 = new Employee(3L, "Henry");
        map2.put(employee5.getName(), employee5);

        System.out.println("Map 1 we have:");
        map1.forEach((key, value) -> System.out.println(key + " " + value.getName()));

        System.out.println("--------------------");
        System.out.println("Map 2 we have:");
        map2.forEach((key, value) -> System.out.println(key + " " + value.getName()));

        System.out.println("--------------------");
        Map<String, Employee> map3 = new HashMap<>(map1);
//        map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(),v2.getName()));
        map2.forEach((key, value) -> map1.merge(key, value, (v1, v2) -> new Employee(v1.getId(), v2.getName())));
        map3.forEach((key, value) -> map1.merge(key, value, (v1, v2) -> new Employee(v1.getId(), v2.getName())));

        System.out.println("--------------------");
        System.out.println("New Map 1 we have:");
        map1.forEach((key, value) -> System.out.println(key + " " + value.getName()));

        System.out.println("--------------------");
        System.out.println("New Map 3 we have:");
        map1.forEach((key, value) -> System.out.println(key + " " + value.getName()));


//        Using Steams
        Map<String, Employee> result = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> new Employee(value2.getId(), value1.getName())));

        System.out.println("--------------------");
        System.out.println("Result Map we have:");
        result.forEach((key, value) -> System.out.println(key + " " + value.getName()));


        Map<String, Employee> result2 = Stream.of(map1, map2).flatMap(map -> map.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> new Employee(v1.getId(), v2.getName())));

        System.out.println("--------------------");
        System.out.println("Result2 Map we have:");
        result2.forEach((key, value) -> System.out.println(key + " " + value.getName()));


//        Simple Streaming
        Map<String, Employee> result3 = map2.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> new Employee(v1.getId(), v2.getName()), () -> new HashMap<>(map1)));

    }


    public static int justMethod(int[] A) {
        // write your code in Java SE 8
        int retVal = 1;
        for (int i = 0; i < A.length; i++) {
            retVal += Integer.signum(A[i]);
        }
        return retVal;
        //int prod = Arrays.stream(A).reduce(1, (a, b) -> (a * b));
    }
}
