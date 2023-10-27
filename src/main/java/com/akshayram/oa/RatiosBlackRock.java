package com.akshayram.oa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The Main class implements an application that reads lines from the standard input and prints them
 * to the standard output.
 */
public class RatiosBlackRock {

  static class Node {
    String source;
    String dest;
    double ratio;

    public Node(String s, String e, double r) {
      this.source = s;
      this.dest = s;
      this.ratio = r;
    }
  }

  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    String source = null, target = null;
    int i = 0;
    String[] rates = null;

    while ((line = in.readLine()) != null) {
      if (i == 0) {
        rates = line.split(";");
      }
      if (i == 1) {
        source = line;
      }
      if (i == 2) {
        target = line;
      }
      i++;
    }

    List<Node> data = new ArrayList<Node>();

    for (String s : rates) {
      String[] temp = s.split(",");
      data.add(new Node(temp[0], temp[1], Double.valueOf(temp[2])));
    }

    System.out.println(getRatio(source, target, data));

    return;
  }

  public static double getRatio(String start, String end, List<Node> data) {
    double ans = -1.0;

    Map<String, Map<String, Double>> map = new HashMap<>();

    for (Node node : data) {

      if ((node.source).equals(start) && (node.dest).equals(end)) {
        ans = node.ratio;
        continue;
      }

      if (!map.containsKey(node.source)) {
        map.put(node.source, new HashMap<>());
      }

      map.get(node.source).put(node.dest, node.ratio);

      if (!map.containsKey(node.dest)) {
        map.put(node.dest, new HashMap<>());
      }

      map.get(node.dest).put(node.source, (1.0 / node.ratio));
    }

    Queue<String> q = new LinkedList<>();
    Queue<Double> val = new LinkedList<>();

    q.offer(start);
    val.offer(1.0);

    Set<String> visited = new HashSet<>();

    while (!q.isEmpty()) {
      String cur = q.poll();
      double num = val.poll();

      if (visited.contains(cur)) {
        continue;
      }

      visited.add(cur);

      if (map.containsKey(cur)) {
        Map<String, Double> next = map.get(cur);
        for (String key : next.keySet()) {
          if (!visited.contains(key)) {
            q.offer(key);
            if (key.equals(end)) {
              double tempResult = num * next.get(key);
              ans = Math.max(ans, tempResult);
            }
            val.offer(num * next.get(key));
          }
        }
      }
    }
    return ans;
  }
}
