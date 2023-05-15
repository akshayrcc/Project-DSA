import heapq

def supply_internet(n, router, ethernet):
    g = [[] for _ in range(n)]
    for i, j, c in ethernet:
        g[i-1].append((j-1, c))
        g[j-1].append((i-1, c))
    heapqueue = [(cost, i) for i, cost in enumerate(router)]
    heapq.heapify(heapqueue)
    visited = set()
    total = 0
    while len(visited) < n:
        cost, u = heapq.heappop(heapqueue)
        if u not in visited:
            visited.add(u)
            total += cost
            for v, c in g[u]:
                if v not in visited:
                    heapq.heappush(heapqueue, (c, v))
    return total


n = 5
router = [1, 2, 1, 5, 3]
ethernet = [    [2, 4, 1],
    [1, 2, 10],
    [1, 3, 31],
    [4, 5, 1]
]

print(supply_internet(n, router, ethernet))

# assert supply_internet(n, router, ethernet) == 8
