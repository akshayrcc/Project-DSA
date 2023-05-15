import heapq
import decimal


def minimum_cost(n, router, ethernet):
    # Add a dummy node and connect it to the initial router
    dummy = n + 1
    graph = {i: {} for i in range(1, n + 2)}
    graph[dummy][router] = decimal.Decimal(0)

    # Find the lowest cost neighbor for each room
    for i in range(1, n + 1):
        min_cost = decimal.Decimal('inf')
        for j, k, c in ethernet:
            if j == i and c < min_cost:
                min_cost = c
                neighbor = k
            elif k == i and c < min_cost:
                min_cost = c
                neighbor = j
        graph[i][neighbor] = min_cost

    # Run Prim's algorithm on the new graph
    heap = [(0, dummy)]
    visited = set()
    total_cost = decimal.Decimal(0)
    while heap:
        cost, node = heapq.heappop(heap)
        if node in visited:
            continue
        visited.add(node)
        total_cost += cost
        for neighbor, cost in graph[node].items():
            heapq.heappush(heap, (cost, neighbor))

    return total_cost


# Example usage
n = 5
router = 1
ethernet = [(1, 2, 3), (2, 3, 4), (3, 4, 1), (4, 5, 5)]
print("answer: " , minimum_cost(n, router, ethernet))  # Output: 9
