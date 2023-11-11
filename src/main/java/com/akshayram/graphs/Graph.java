package com.akshayram.graphs;

import com.akshayram.missing.Pair;

import java.util.*;

public class Graph {
  List<List<Pair<Integer, Integer>>> adjList;

  public Graph(int n, int[][] edges) {
    adjList = new ArrayList<>();
    for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
    for (int[] e : edges) adjList.get(e[0]).add(new Pair<>(e[1], e[2]));
  }

  public void addEdge(int[] edge) {
    adjList.get(edge[0]).add(new Pair<>(edge[1], edge[2]));
  }

  public int shortestPath(int node1, int node2) {
    int n = adjList.size();
    var pq = new PriorityQueue<List<Integer>>(Comparator.comparingInt(list -> list.get(0)));
    int[] costForNode = new int[n];
    Arrays.fill(costForNode, Integer.MAX_VALUE);
    costForNode[node1] = 0;
    pq.offer(Arrays.asList(0, node1));

    while (!pq.isEmpty()) {
      var curr = pq.poll();
      int currCost = curr.get(0);
      int currNode = curr.get(1);

      if (currCost > costForNode[currNode]) {
        continue;
      }
      if (currNode == node2) {
        return currCost;
      }
      for (var neighbor : adjList.get(currNode)) {
        int neighborNode = neighbor.getKey();
        int cost = neighbor.getValue();
        int newCost = currCost + cost;

        if (newCost < costForNode[neighborNode]) {
          costForNode[neighborNode] = newCost;
          pq.offer(Arrays.asList(newCost, neighborNode));
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    // Test Case 1
    int n1 = 5;
    int[][] edges1 = {{0, 1, 2}, {0, 2, 4}, {1, 3, 3}, {2, 4, 1}, {3, 4, 7}};
    Graph graph1 = new Graph(n1, edges1);
    System.out.println("Shortest Path (Test Case 1): " + graph1.shortestPath(0, 4)); // Output: 6

    // Test Case 2
    int n2 = 6;
    int[][] edges2 = {{0, 1, 1}, {0, 2, 4}, {1, 3, 2}, {2, 3, 5}, {3, 4, 1}, {4, 5, 3}};
    Graph graph2 = new Graph(n2, edges2);
    System.out.println("Shortest Path (Test Case 2): " + graph2.shortestPath(0, 5)); // Output: 10

    // Test Case 3 (Unreachable nodes)
    int n3 = 4;
    int[][] edges3 = {{0, 1, 1}, {0, 2, 3}, {2, 3, 2}};
    Graph graph3 = new Graph(n3, edges3);
    System.out.println("Shortest Path (Test Case 3): " + graph3.shortestPath(1, 3)); // Output: -1
  }
}
