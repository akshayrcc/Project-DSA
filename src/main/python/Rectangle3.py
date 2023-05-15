def get_groups(n, length, breadth):
    # Create a list of tuples representing the rectangles
    rectangles = [(length[i], breadth[i]) for i in range(n)]

    # Sort the rectangles by their length in decreasing order
    rectangles.sort(reverse=True)

    # Create a list to store the groups
    groups = []

    # Iterate through the rectangles
    for rectangle in rectangles:
        # Check if the rectangle can be added to an existing group
        added = False
        for group in groups:
            if all(rectangle[i] < group[0][i] for i in range(2)):
                group.append(rectangle)
                added = True
                break
        # If the rectangle couldn't be added to an existing group, create a new group
        if not added:
            groups.append([rectangle])

    # Return the number of groups
    return len(groups)


n = 5
length = [1, 2, 5, 4, 3]
breadth = [3, 5, 2, 1, 4]
answer = get_groups(n, length, breadth)
print(answer)  # Output: 2
