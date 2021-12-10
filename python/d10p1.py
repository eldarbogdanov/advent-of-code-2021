import sys

map = {
    ']': '[',
    '}': '{',
    ')': '(',
    '>': '<'
}
cost = {
    ')': 3,
    ']': 57,
    '}': 1197,
    '>': 25137
}
with open("test.in") as f:
    strings = f.read().splitlines()
    ans = 0
    for s in strings:
        stack = []
        for c in s:
            if c in map:
                if len(stack) == 0 or stack[-1] != map[c]:
                    ans += cost[c]
                    break
                stack.pop()
            else:
                stack.append(c)
    print(ans)
