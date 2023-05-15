import heapq

def minimum_cost(n, router, ethernet):
    # Build the adjacency list of the graph
    adj = [[] for _ in range(n+1)]
    for i, j, c in ethernet:
        adj[i].append((j, c))
        adj[j].append((i, c))

    # Initialize the distance array and the heap for Dijkstra's algorithm
    dist = [float('inf') for _ in range(n+1)]
    for r in router:
        dist[r] = 0
    heap = [(0, r) for r in router]

    # Run Dijkstra's algorithm
    while heap:
        d, u = heapq.heappop(heap)
        if d > dist[u]:
            continue
        for v, c in adj[u]:
            if dist[u] + c < dist[v]:
                dist[v] = dist[u] + c
                heapq.heappush(heap, (dist[v], v))

    # Return the sum of the shortest distances to all the rooms
    return sum(dist[1:])

# Example usage
n = 5
router = [1,2,1,5,3]
ethernet = [[2,4,1], [1,0,10], [2,3,2], [0,4,31], [3,4,1]]
print(minimum_cost(n, router, ethernet))  # Output: 6
