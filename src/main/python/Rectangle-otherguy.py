def solution(length, breadth):
    rectangles = [(length[i], breadth[i]) for i in range(len(length))]
    rectangles.sort(key=lambda l: l[0] * l[1], reverse=True)
    groups = []
    for rectangle in rectangles:
        isAppended = False
        for group in groups:
            isOverlap = False
            for r in group:
                if rectangle[0] < r[0] and rectangle[1] < r[1]:
                    isOverlap = True
                    break
            if not isOverlap:
                group.append(rectangle)
                isAppended = True
                break
        if not isAppended:
            groups.append([rectangle])
    return len(groups)


n = 5
length = [1, 2, 5, 4, 3]
breadth = [3, 5, 2, 1, 4]
answer = solution(length, breadth)
print(answer)  # Output: 2
