def get_groups(length, breadth):
    # Create a list of tuples representing the rectangles
    rectangles = [(length[i], breadth[i]) for i in range(len(length))]

    # Create a dictionary to store the groups, with each group represented as a set of indices
    groups = {}

    # Iterate through the rectangles
    for i, rectangle in enumerate(rectangles):
        # Find all existing groups that the rectangle does not overlap with
        non_overlapping_groups = []
        for group in groups.values():
            overlaps = False
            for index in group:
                if rectangle[0] >= rectangles[index][0] and rectangle[1] >= rectangles[index][1]:
                    overlaps = True
                    break
            if not overlaps:
                non_overlapping_groups.append(group)

        # If the rectangle does not overlap with any existing group, create a new group for it
        if len(non_overlapping_groups) == 0:
            groups[i] = set([i])
        # If the rectangle overlaps with some existing groups, add it to the first non-overlapping group
        else:
            non_overlapping_groups[0].add(i)
            # Merge any other non-overlapping groups that overlap with this group
            for group in non_overlapping_groups[1:]:
                if not any(rectangles[j][0] >= rectangle[0] and rectangles[j][1] >= rectangle[1] for j in group):
                    non_overlapping_groups[0].update(group)

    # Return the number of groups
    return len(groups)


# length = [1, 2, 5, 4, 3]
# breadth = [3, 5, 2, 1, 4]
# answer = get_groups(n, length, breadth)
# print(answer)  # Output: 2


length = [6, 8, 3, 8, 10, 10, 2, 10, 7, 2]
breadth = [4, 9, 9, 6, 5, 10, 7, 8, 7, 8]
answer = get_groups(length, breadth)
print(answer)  # Output: 2