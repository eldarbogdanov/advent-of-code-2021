import sys

map = {
    ']': '[',
    '}': '{',
    ')': '(',
    '>': '<'
}
cost = {
    '(': 1,
    '[': 2,
    '{': 3,
    '<': 4
}
costs = []
with open("test.in") as f:
    strings = f.read().splitlines()
    ans = 0
    for s in strings:
        good = True
        stack = []
        for c in s:
            if c in map:
                if len(stack) == 0 or stack[-1] != map[c]:
                    good = False
                    break
                stack.pop()
            else:
                stack.append(c)
        if not good:
            continue
        cur = 0
        for c in reversed(stack):
            cur = cur * 5 + cost[c]
        costs.append(cur)
    costs = sorted(costs)
    print(costs)
    print(costs[int(len(costs) / 2)])
