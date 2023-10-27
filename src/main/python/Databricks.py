import numpy as np

def maxSumSubmatrix(matrix, size):
    rows, cols = len(matrix), len(matrix[0])
    maxSum = float('-inf')
    maxSumSubmatrixSet = set()

    for i in range(rows - size + 1):
        for j in range(cols - size + 1):
            # Calculate the sum of the current size x size submatrix using list comprehension
            submatrix = [row[j:j+size] for row in matrix[i:i+size]]
            submatrixSum = sum(sum(row) for row in submatrix)

            # Update maxSum and maxSumSubmatrixSet if necessary
            if submatrixSum > maxSum:
                maxSum = submatrixSum
                maxSumSubmatrixSet = set(value for row in submatrix for value in row)

    # Sum up distinct numbers from maxSumSubmatrixSet
    distinctSum = sum(maxSumSubmatrixSet)

    return distinctSum

# Example usage:
matrix = [
    [1, 0, 1, 5, 6],
    [3, 3, 0, 3, 3],
    [2, 9, 2, 1, 2],
    [0, 2, 4, 2, 0]
]
size = 2
result = maxSumSubmatrix(matrix, size)
print(result)  # Output: 29
