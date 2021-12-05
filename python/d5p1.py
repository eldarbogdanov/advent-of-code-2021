import sys

lines = []
with open("test.in") as f:
    strings = f.read().splitlines()
    for s in strings:
        a = s.split(" -> ")[0]
        b = s.split(" -> ")[1]
        x1 = int(a.split(",")[0])
        y1 = int(a.split(",")[1])
        x2 = int(b.split(",")[0])
        y2 = int(b.split(",")[1])
        if x1 > x2:
            t = x1
            x1 = x2
            x2 = t
        if y1 > y2:
            t = y1
            y1 = y2
            y2 = t
        lines.append((x1, y1, x2, y2))

ans = 0
for i in range(0, 1001):
    print(i)
    for j in range(0, 1001):
        cnt = 0
        # print(len(lines))
        for line in lines:
            if line[0] == line[2] and i == line[0] and line[1] <= j <= line[3] or \
                    line[1] == line[3] and j == line[1] and line[0] <= i <= line[2]:
                cnt += 1
        if cnt > 1:
            # print(cnt, i, j)
            ans += 1
print(ans)
