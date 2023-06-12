package com.cybertron.stringops;

import java.util.*;

public class PathsSubPaths {
    public static String solution(String[] paths) {
        if (paths.length == 0) {
            return "";
        }
        String[] simplifiedPaths = new String[paths.length];
        for (int i = 0; i < paths.length; i++) {
            simplifiedPaths[i] = simplifyPath(paths[i]);
        }
        String shortestPath = Arrays.stream(simplifiedPaths).min(Comparator.comparing(String::length)).orElse("");
        String[] shortestPathParts = shortestPath.split("/");
        StringBuilder commonSuffix = new StringBuilder();
        for (int i = shortestPathParts.length - 1; i >= 0; i--) {
            String suffix = shortestPathParts[i];
            boolean allMatch = true;
            for (String path : simplifiedPaths) {
                String[] pathParts = path.split("/");
                if (pathParts.length <= i || !pathParts[i].equals(suffix)) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                commonSuffix.insert(0, suffix);
                commonSuffix.insert(0, "/");
            } else {
                break;
            }
        }
        return commonSuffix.toString();
    }

    private static String simplifyPath(String path) {
        String[] parts = path.split("/");
        List<String> stack = new ArrayList<>();
        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.remove(stack.size() - 1);
                }
            } else if (!part.equals(".") && !part.isEmpty()) {
                stack.add(part);
            }
        }
        return "/" + String.join("/", stack);
    }
    static int count = Integer.MAX_VALUE;
    public static void main(String[] args) {
        String[] paths1 = {"/a/folder1/../folder1/a/leaf.txt", "/b/folder2/../folder1/a/leaf.txt", "/a/folder3/folder1/folder1/a/leaf.txt"};
        String expected1 = "/folder1/a/leaf.txt";
        String result1 = solution2(paths1);
        System.out.println(result1.equals(expected1)); // true

        String[] paths2 = {"/root/folder1/b/../a", "/root/folder1/a/leaf.txt", "/root/folder1/a/b/../a/branch"};
        String expected2 = "";
        String result2 = solution2(paths2);
        System.out.println(result2.equals(expected2)); // true

        String[] paths3 = {};
        String expected3 = "";
        String result3 = solution2(paths3);
        System.out.println(result3.equals(expected3)); // true

        //-----------------------
        String[] paths4 = {"/a/folder1/../folder1/a/leaf.txt", "/b/folder2/../folder1/a/leaf.txt", "/a/folder3/folder1/folder1/a/leaf.txt"};
        String expected4 = "/folder1/a/leaf.txt";
        String result4 = solution2(paths4);
        System.out.println(result4.equals(expected4)); // true
    }

    private static String solution2(String[] paths) {
        if(paths == null || paths.length == 0) return "";
        List<List<String>> shortPathTokens = getShortPaths(paths);
        Stack<String> stack = new Stack<>();

        boolean flag = true;
        count = Integer.MAX_VALUE;
        while(count > 0){
            String value = shortPathTokens.get(0).get(shortPathTokens.get(0).size() -1);
            for(List<String> tokens : shortPathTokens){
                String token = tokens.remove(tokens.size() - 1);
                flag = flag && value.equals(token);
            }
            if(flag)
                stack.push(value);
            else break;
            count--;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append("/").append(stack.pop());
        return sb.toString();
    }


    private static List<List<String>> getShortPaths(String[] paths) {
        List<List<String>> shortPathTokens = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(String path : paths){
            String[] tokens = path.split("/");
            for(String token : tokens){
                if("..".equals(token)){
                    if(!list.isEmpty()) list.remove(list.size() - 1);
                }
                else list.add(token);
            }
            count = Math.min(count, list.size());
            shortPathTokens.add(list);
            list = new ArrayList<>();
        }
        return shortPathTokens;
    }
}
