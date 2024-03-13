package com.akshayram.graphs;

import java.util.HashMap;

public class Undirected {

    private final HashMap<Integer, UndirectedGraphNode> visitedMap = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    //TC: O(V + E)
    //SC: O(V)
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;

        if (visitedMap.containsKey(node.val)) {
            return visitedMap.get(node.val);
        }

        UndirectedGraphNode clone = new UndirectedGraphNode(node.val);
        visitedMap.put(clone.val, clone);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }
}
