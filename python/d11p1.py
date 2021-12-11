import sys

with open("test.in") as f:
    board = f.read().splitlines()

n = len(board)
m = len(board[0])
print(board, n, m)
ans = 0
di = [-1, -1, -1, 0, 1, 1, 1, 0]
dj = [-1, 0, 1, 1, 1, 0, -1, -1]

for step in range(100):
    flashed = []
    for i in range(n):
        flashed.append([False] * m)

    for i in range(n):
        for j in range(m):
            board[i] = board[i][:j] + chr(ord(board[i][j]) + 1) + board[i][j + 1:]

    print(board)

    change = True
    while change:
        change = False
        for i in range(n):
            for j in range(m):
                if board[i][j] == ':':
                    flashed[i][j] = True
                    board[i] = board[i][:j] + '0' + board[i][j + 1:]
                    change = True
                    ans += 1
                    for k in range(8):
                        newi = i + di[k]
                        newj = j + dj[k]
                        if newi < 0 or newj < 0 or newi >= n or newj >= m:
                            continue
                        if not flashed[newi][newj] and board[newi][newj] != ':':
                            board[newi] = board[newi][:newj] + chr(ord(board[newi][newj]) + 1) + board[newi][newj + 1:]

    print(board)

print(ans)
