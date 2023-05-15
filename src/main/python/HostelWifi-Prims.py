import heapq

def prim(edges, n, router, start):
    heap = []
    visited = set()
    for v, w in edges[start]:
        heapq.heappush(heap, (w, v))
    visited.add(start)
    cost = 0
    while heap:
        w, u = heapq.heappop(heap)
        if u in visited:
            continue
        visited.add(u)
        cost += w
        for v, w in edges[u]:
            if v not in visited:
                heapq.heappush(heap, (w, v))
    for i in range(n):
        if i in router and i not in visited:
            return float('inf')
    return cost

def supply_internet(n, router, ethernet):
    edges = [[] for _ in range(n)]
    for u, v, w in ethernet:
        edges[u-1].append((v-1, w))
        edges[v-1].append((u-1, w))
    router_set = set(router)
    min_cost = float('inf')
    for r in router_set:
        cost = prim(edges, n, router_set, r-1)
        min_cost = min(min_cost, cost)
    return min_cost


n = 5
router = [1, 2, 1, 5, 3]
ethernet = [    [2, 4, 1],
    [1, 2, 10],
    [1, 3, 31],
    [4, 5, 1]
]

print(supply_internet(n, router, ethernet))
# assert supply_internet(n, router, ethernet) == 8
