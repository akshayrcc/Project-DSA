package com.akshayram.contests;

import java.util.*;

public class LockMonitor {

    public static int checkLogHistory(List<String> events) {
        Stack<Integer> acquiredStack = new Stack<>();
        Set<Integer> acquiredSet = new HashSet<>();

        int n = events.size();

        for (int i = 0; i < n; i++) {
            String event = events.get(i);
            String[] parts = event.split(" ");

            String operation = parts[0];
            int lockId = Integer.parseInt(parts[1]);

            if (operation.equals("ACQUIRE")) {
                if (acquiredSet.contains(lockId)) {
                    return i + 1; // violation: acquiring an already held lock
                } else {
                    acquiredStack.push(lockId);
                    acquiredSet.add(lockId);
                }
            } else if (operation.equals("RELEASE")) {
                if (acquiredStack.isEmpty() || acquiredStack.peek() != lockId) {
                    return i + 1; // violation: releasing a lock not acquired previously or out of order
                } else {
                    acquiredStack.pop();
                    acquiredSet.remove(lockId);
                }
            }
        }

        if (!acquiredStack.isEmpty()) {
            return n + 1; // dangling acquired locks at termination
        }

        return 0; // no violations found
    }

    public static void main(String[] args) {
        List<String> events1 = Arrays.asList(
                "ACQUIRE 364",
                "ACQUIRE 84",
                "RELEASE 84",
                "RELEASE 364"
        );
        System.out.println(checkLogHistory(events1)); // Output: 0

        List<String> events2 = Arrays.asList(
                "ACQUIRE 364",
                "ACQUIRE 84",
                "RELEASE 364",
                "RELEASE 84"
        );
        System.out.println(checkLogHistory(events2)); // Output: 3

        List<String> events3 = Arrays.asList(
                "ACQUIRE 123",
                "ACQUIRE 364",
                "ACQUIRE 84",
                "RELEASE 84",
                "RELEASE 364",
                "ACQUIRE 456"
        );
        System.out.println(checkLogHistory(events3)); // Output: 7

        List<String> events4 = Arrays.asList(
                "ACQUIRE 123",
                "ACQUIRE 364",
                "ACQUIRE 84",
                "RELEASE 84",
                "RELEASE 364",
                "ACQUIRE 789",
                "RELEASE 456",
                "RELEASE 123"
        );
        System.out.println(checkLogHistory(events3)); // Output: 7
    }
}