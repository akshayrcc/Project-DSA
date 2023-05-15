import heapq


def min_operations(execution_time, x, y):
    n = len(execution_time)
    remaining_time = [(t, i) for i, t in enumerate(execution_time)]
    heapq.heapify(remaining_time)
    completed = set()
    operations = 0

    while remaining_time:
        major_time, major_job = heapq.heappop(remaining_time)
        if major_job in completed:
            continue

        operations += 1
        completed.add(major_job)

        major_time -= x
        if major_time > 0:
            heapq.heappush(remaining_time, (major_time, major_job))

        for t, i in remaining_time:
            if i in completed:
                continue

            t -= y
            if t <= 0:
                completed.add(i)
            else:
                heapq.heappush(remaining_time, (t, i))

    return operations

if __name__ == '__main__':
    print("Hello")
    # Test Case 1
    executionTime = [3, 4, 1, 7, 6]
    x = 4
    y = 2
    print(min_operations(executionTime, x, y))  # 3

    # Test Case 2
    executionTime = [3, 3, 6, 3, 9]
    x = 3
    y = 3
    print(min_operations(executionTime, x, y))  # 5

    # Test Case 3
    executionTime = [2, 3, 5]
    x = 3
    y = 1
    print(min_operations(executionTime, x, y))  # == 5
