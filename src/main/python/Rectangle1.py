class Rectangle:
    def __init__(self, length, breadth):
        self.length = length
        self.breadth = breadth

def overlaps(rect1, rect2):
    return (rect1.length >= rect2.length and rect1.breadth >= rect2.breadth) or \
           (rect1.length >= rect2.breadth and rect1.breadth >= rect2.length)

def partition_rectangles(rectangles):
    groups = []
    for rect in rectangles:
        for group in groups:
            overlap = False
            for other_rect in group:
                if overlaps(rect, other_rect):
                    overlap = True
                    break
            if not overlap:
                group.append(rect)
                break
        else:
            groups.append([rect])
    return len(groups)

if __name__ == '__main__':
    rectangles = [    Rectangle(3, 5),
                      Rectangle(2, 4),
                      Rectangle(3, 4),
                      Rectangle(2, 5),
                      Rectangle(3, 5),
                      Rectangle(4, 2)]

    num_groups = partition_rectangles(rectangles)
    print("Heyy" , num_groups) # expected output: 3