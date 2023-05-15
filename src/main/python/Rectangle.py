class Rectangle:
    def __init__(self, length, breadth):
        self.length = length
        self.breadth = breadth

def overlaps(rect1, rect2):
    return rect1.length >= rect2.length and rect1.breadth >= rect2.breadth

def partition_rectangles(rectangles):
    rectangles = sorted(rectangles, key=lambda rect: (-rect.length, -rect.breadth))
    f = [float('inf')] * (len(rectangles) + 1)
    f[0] = 0
    for i in range(1, len(rectangles) + 1):
        for j in range(i):
            if all(not overlaps(rectangles[i-1], rectangles[k]) for k in range(j, i)):
                f[i] = min(f[i], f[j] + 1)
    return f[len(rectangles)]

if __name__ == '__main__':
    rectangles = [    Rectangle(3, 5),
                      Rectangle(2, 4),
                      Rectangle(3, 4),
                      Rectangle(2, 5),
                      Rectangle(3, 5),
                      Rectangle(4, 2)]

    num_groups = partition_rectangles(rectangles)
    print("Heyy" , num_groups) # expected output: 3