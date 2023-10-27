# Function to find maximum sum submatrix
def maxSumSubmatrix(matrix, size):
    rows = len(matrix)
    cols = len(matrix[0])
    maxSum = float('-inf')
    maxSumSubmatrixSet = set()

    for i in range(rows - size + 1):
        for j in range(cols - size + 1):
            # Calculate the sum of the current size x size submatrix
            submatrixSum = 0
            submatrixSet = set()
            for x in range(i, i + size):
                for y in range(j, j + size):
                    submatrixSum += matrix[x][y]
                    submatrixSet.add(matrix[x][y])

            # Update maxSum and maxSumSubmatrixSet if necessary
            if submatrixSum > maxSum:
                maxSum = submatrixSum
                maxSumSubmatrixSet = set(submatrixSet)
            if submatrixSum == maxSum:
                maxSumSubmatrixSet.update(submatrixSet)

    # Sum up distinct numbers from maxSumSubmatrixSet
    distinctSum = sum(maxSumSubmatrixSet)

    return distinctSum



# Driver Code
if __name__ == '__main__':
    matrix = [
        [1, 0, 1, 5, 6],
        [3, 3, 0, 3, 3],
        [2, 9, 2, 1, 2],
        [0, 2, 4, 2, 0]
    ]

    print(maxSumSubmatrix(matrix, 2))