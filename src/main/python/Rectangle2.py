from collections import defaultdict

class Rectangle2:
    def __init__(self, length, breadth):
        self.length = length
        self.breadth = breadth

def overlaps(rect1, rect2):
    return (rect1.length >= rect2.length and rect1.breadth >= rect2.breadth) or \
           (rect1.length >= rect2.breadth and rect1.breadth >= rect2.length)

def partition_rectangles(rectangles):
    graph = defaultdict(set)
    for i, rect1 in enumerate(rectangles):
        for j, rect2 in enumerate(rectangles):
            if i != j and overlaps(rect1, rect2):
                graph[i].add(j)
                graph[j].add(i)
    colors = [-1] * len(rectangles)
    for i in range(len(rectangles)):
        neighbors = set(j for j in graph[i] if colors[j] != -1)
        available_colors = set(range(len(rectangles))) - set(colors[j] for j in neighbors)
        colors[i] = min(available_colors)
    return max(colors) + 1


if __name__ == '__main__':
    rectangles = [    Rectangle2(3, 5),
                      Rectangle2(2, 4),
                      Rectangle2(3, 4),
                      Rectangle2(2, 5),
                      Rectangle2(3, 5),
                      Rectangle2(4, 2)]

    num_groups = partition_rectangles(rectangles)
    print("Heyy" , num_groups) # expected output: 3