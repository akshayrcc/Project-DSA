package com.akshayram.graphs;

import java.util.*;

class ShortestPathProb {

  class Edge {
    char dest;
    int cost;

    Edge(char dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }

  class Graph {
    private Map<Character, List<Edge>> adjList;

    public Graph() {
      adjList = new HashMap<>();
    }

    public void addEdge(char source, char dest, int cost) {
      if (!adjList.containsKey(source)) {
        adjList.put(source, new LinkedList<>());
      }
      adjList.get(source).add(new Edge(dest, cost));
    }

    // Dijkstra's Algorithm to find the shortest path
    public int dijkstra(char start, char end) {
      // Priority queue to hold vertices with cost as priority
      PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
      queue.add(new Edge(start, 0));

      Map<Character, Integer> distances = new HashMap<>();
      Set<Character> visited = new HashSet<>();

      // Initialize distances as infinity
      for (char vertex : adjList.keySet()) {
        distances.put(vertex, Integer.MAX_VALUE);
      }
      distances.put(start, 0);

      while (!queue.isEmpty()) {
        char current = queue.poll().dest;

        if (!visited.add(current)) {
          continue; // Skip if already visited
        }

        // Process all neighbors
        for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
          if (visited.contains(edge.dest)) {
            continue; // Skip if neighbor already visited
          }

          int newDist = distances.get(current) + edge.cost;
          if (newDist < distances.getOrDefault(edge.dest, Integer.MAX_VALUE)) {
            distances.put(edge.dest, newDist);
            queue.add(new Edge(edge.dest, newDist));
          }
        }
      }

      return distances.getOrDefault(end, -1); // Return -1 if no path exists
    }
  }

//  public  long minimumCost(String source, String target, char[] start, char[] end, int[] cost) {
//
//
//    return shortestPathCost;
//  }

  public static void main(String[] args){

//      String source = "aabbddccbc";
//      String target = "abbbaabaca";
//      char[] start = {'a', 'b', 'c', 'b', 'a', 'd'};
//      char[] end = {'d', 'c', 'b', 'd', 'b', 'b'};
//      int[] cost = {3, 8, 7, 6, 7, 10};
//
//      Graph graph = new Graph();
//      for (int i = 0; i < start.length; i++) {
//          graph.addEdge(start[i], end[i], cost[i]);
//      }
//
//      long shortestPathCost = 0;
//
//      for (int i = 0; i < source.length(); i++) {
//          char source_1 = source.charAt(i); // Starting node
//          char destination_1 = target.charAt(i); // Destination node
//          shortestPathCost += graph.dijkstra(source_1, destination_1);
//      }
//
////      long result = minimumCost(source, target, start, end, cost);
//      System.out.println("Minimum cost to transform source to target is: " + result);

  }
}
