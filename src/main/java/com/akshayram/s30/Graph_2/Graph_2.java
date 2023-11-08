import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    // TC: O(n^2). SC: O(n)
    int[] colors;

    public int minMalwareSpread(int[][] graph, int[] initials) {
        if (graph == null || graph.length == 0) {
            return 0;
        }
        int n = graph.length;
        this.colors = new int[n];
        Arrays.fill(colors, -1);
        int cl = 0;
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                dfs(graph, i, cl);
            }
            cl++;
        }

        int[] groups = new int[cl];
        for (int color : colors) {
            groups[color]++;
        }

        int[] initGroups = new int[cl];

        for (int node : initials) {
            int c = colors[node];
            initGroups[c]++;
        }

        int result = Integer.MAX_VALUE;

        for (int node : initials) {
            int col = colors[node];
            int count = initGroups[col];
            if (count == 1) {
                if (result == Integer.MAX_VALUE) {
                    result = node;
                } else if (groups[colors[result]] < groups[col]) {
                    result = node;
                } else if (groups[colors[result]] == groups[col] && node < result) {
                    result = node;
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            for (int node : initials) {
                result = Math.min(result, node);
            }
        }

        return result;
    }

    private void dfs(int[][] graph, int i, int cl) {
        if (colors[i] != -1)
            return;
        colors[i] = cl;
        for (int j = 0; j < graph.length; j++) {
            if (i == j)
                continue;
            if (graph[i][j] == 1) {
                dfs(graph, j, cl);
            }
        }
    }

    // TC: O(v + e) SC: O(v + e)
    // Tarjan's algorithm - Connections in a cycle are not critical
    HashMap<Integer, List<Integer>> hmap;
    int[] discovery;
    int[] lowest;
    int time;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.result = new ArrayList<>();
        this.hmap = new HashMap<>();

        this.discovery = new int[n];
        Arrays.fill(this.discovery, -1);

        this.time = 0;
        this.lowest = new int[n];

        buildGraph(connections, n);
        dfs(0, 0);
        return result;
    }

    private void buildGraph(List<List<Integer>> connections, int n) {
        for (int i = 0; i < n; i++) {
            hmap.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : connections) {
            int first = edge.get(0);
            int second = edge.get(1);
            hmap.get(first).add(second);
            hmap.get(second).add(first);
        }
    }

    private void dfs(int v, int u) {
        if (discovery[v] != -1) {
            return;
        }
        discovery[v] = time;
        lowest[v] = time;
        time++;
        for (int ne : hmap.get(v)) {
            if (ne == u)
                continue;
            dfs(ne, v);
            if (lowest[ne] > discovery[v]) {
                result.add(Arrays.asList(ne, v));
            }
            lowest[v] = Math.min(lowest[ne], lowest[v]);
        }
    }
}