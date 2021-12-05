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
        if x1 > x2 and y1 == y2:
            t = x1
            x1 = x2
            x2 = t
        if x1 == x2 and y1 > y2:
            t = y1
            y1 = y2
            y2 = t
        lines.append((x1, y1, x2, y2))

cnt = []
for i in range(1001):
    cnt.append([0] * 1001)

for line in lines:
    x1, y1, x2, y2 = line[0], line[1], line[2], line[3]
    if x1 == x2:
        for i in range(y1, y2 + 1):
            cnt[x1][i] += 1
    elif y1 == y2:
        for i in range(x1, x2 + 1):
            cnt[i][y1] += 1
    else:
        xdir = 1 if x1 < x2 else -1
        ydir = 1 if y1 < y2 else -1
        # print(x1, y1, x2, y2, xdir, ydir)
        x = x1
        y = y1
        while x != x2:
            # print(x, y)
            cnt[x][y] += 1
            x += xdir
            y += ydir
        cnt[x][y] += 1

ans = 0
for i in range(len(cnt)):
    for j in range(len(cnt[0])):
        if cnt[i][j] > 1:
            # print(i, j, cnt[i][j])
            ans += 1
print(ans)
