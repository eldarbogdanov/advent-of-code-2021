import sys

with open("../test.in") as f:
    nums = f.read().splitlines()


def parse(s, left):
    if s[left] >= '0' and s[left] <= '9':
        return Node(None, None, ord(s[left]) - ord('0')), left + 1
    if s[left] == '[':
        leftNode, newIndex = parse(s, left + 1)
        rightNode, newIndex = parse(s, newIndex + 1)
        return Node(leftNode, rightNode, None), newIndex + 1


class Node(object):
    def __init__(self, left, right, value):
        self.left = left
        self.right = right
        self.value = value

    def print(self):
        if self.value is not None:
            return str(self.value)
        else:
            return "[" + self.left.print() + "," + self.right.print() + "]"

    def magnitude(self):
        if self.value is not None:
            return self.value
        return self.left.magnitude() * 3 + self.right.magnitude() * 2

    def reduce(self):
        global lastValue
        global toAdd
        global exploded
        # print("reducing", self.print())
        while True:
            exploded = False
            lastValue = None
            rec(self, 0)
            if not exploded:
                if not rec2(self):
                    break
                # print("split   ", self.print())
            else:
                # print("exploded", self.print())
                pass
        return self

    def add(self, other):
        res = Node(self, other, None)
        res.reduce()
        return res


def rec(node, depth):
    global lastValue
    global toAdd
    global exploded
    if exploded:
        if toAdd is None:
            return
        if node.value is None:
            rec(node.left, depth + 1)
            rec(node.right, depth + 1)
        else:
            node.value += toAdd
            toAdd = None
        return

    if depth >= 4 and node.value is None: #and node.left.value is not None:
        if lastValue is not None:
            lastValue.value += node.left.value
        toAdd = node.right.value
        node.left = node.right = None
        node.value = 0
        exploded = True
        return
    if node.value is None:
        rec(node.left, depth + 1)
        rec(node.right, depth + 1)
    else:
        lastValue = node


def rec2(node):
    if node.value is not None and node.value >= 10:
        node.left = Node(None, None, node.value // 2)
        node.right = Node(None, None, node.value - node.value // 2)
        node.value = None
        return True
    if node.value is not None:
        return False
    if rec2(node.left):
        return True
    if rec2(node.right):
        return True
    return False


cur, discard = parse(nums[0], 0)
print(cur.print())
cur.reduce()
for i in range(1, len(nums)):
    other, discard = parse(nums[i], 0)
    print(other.print())
    cur = cur.add(other)
    print(cur.print())

print(cur.print(), cur.magnitude())

maxMagnitude = 0
for num1 in nums:
    for num2 in nums:
        if num1 == num2:
            continue
        x1, _ = parse(num1, 0)
        x2, _ = parse(num2, 0)
        maxMagnitude = max(maxMagnitude, x1.add(x2).magnitude())
print(maxMagnitude)
