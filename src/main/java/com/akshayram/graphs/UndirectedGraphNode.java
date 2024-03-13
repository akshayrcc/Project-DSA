package com.akshayram.graphs;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {

    public int val;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode() {
        val = 0;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }

    public UndirectedGraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }

    public UndirectedGraphNode(int _val, ArrayList<UndirectedGraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
