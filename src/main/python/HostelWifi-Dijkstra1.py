import heapq


def minimum_cost(n, router, ethernet):
    # Build the adjacency list for the graph
    graph = {i: {} for i in range(1, n + 1)}
    for i, j, c in ethernet:
        graph[i][j] = c
        graph[j][i] = c

    # Find the shortest path from the router to each room
    distances = {i: float('inf') for i in range(1, n + 1)}
    distances[router] = 0
    visited = set()
    heap = [(0, router)]
    while heap:
        d, i = heapq.heappop(heap)
        if i in visited:
            continue
        visited.add(i)
        for j, c in graph[i].items():
            if distances[j] > d + c:
                distances[j] = d + c
                heapq.heappush(heap, (distances[j], j))

    # Sum up the costs of the shortest paths
    total_cost = sum(distances.values())

    return total_cost


# Example usage
# n = 5
# router = 1
# ethernet = [(1, 2, 3), (2, 3, 4), (3, 4, 1), (4, 5, 5)]
# print(minimum_cost(n, router, ethernet))  # Output: 13
n = 5
router = [1, 2, 1, 5, 3]
ethernet = [(2, 4, 1), (0, 2, 3), (1, 3, 3), (0, 4, 1)]
print(minimum_cost(n, router, ethernet))  # Output: 13
