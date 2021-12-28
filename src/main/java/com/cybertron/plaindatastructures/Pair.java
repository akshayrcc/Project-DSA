package com.cybertron.plaindatastructures;

import java.util.AbstractMap;
import java.util.Map;

public class Pair
{
   // Return a map entry (key-value pair) from the specified values
    public static <T, U> Map.Entry<T, U> of(T first, U second)
    {
        return new AbstractMap.SimpleEntry<>(first, second);
    }
    
    // Return an immutable singleton map containing only the specified
    // key-value pair mapping
    /*public static <T, U> Map<T, U> of(T first, U second)
    {
        return Collections.singletonMap(first, second);
    }*/
    
}

/*How to use
 * 
 * Set<Map<String, Integer>> entries = new HashSet<>();
 
        entries.add(Pair.of("Java", 50));
        entries.add(Pair.of("C++", 30));
 
        System.out.println(entries);
 * */
