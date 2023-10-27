# Python3 program for the above approach

# Function to find maximum sum submatrix
def maxSubmatrixSum(matrix):
    # Stores the number of rows
    # and columns in the matrix
    r = len(matrix)
    c = len(matrix[0])

    # Stores maximum submatrix sum
    maxSubmatrix = 0

    # Take each row as starting row
    for i in range(r):

        # Take each column as the
        # starting column
        for j in range(c):

            # Take each row as the
            # ending row
            for k in range(i, r):

                # Take each column as
                # the ending column
                for l in range(j, c):

                    # Stores the sum of submatrix
                    # having topleft index(i, j)
                    # and bottom right index (k, l)
                    sumSubmatrix = 0

                    # Iterate the submatrix
                    # row-wise and calculate its sum
                    for m in range(i, k + 1):
                        for n in range(j, l + 1):
                            sumSubmatrix += matrix[m][n]

                    # Update the maximum sum
                    maxSubmatrix = max(maxSubmatrix, sumSubmatrix)

    # Print the answer
    print(maxSubmatrix)


# Driver Code
if __name__ == '__main__':
    matrix = [
        [1, 0, 1, 5, 6],
        [3, 3, 0, 3, 3],
        [2, 9, 2, 1, 2],
        [0, 2, 4, 2, 0]
    ]

    maxSubmatrixSum(matrix)

# This code is contributed by mohit kumar 29.
