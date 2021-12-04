import sys

with open("test.in") as f:
    s = f.read().splitlines()

nums = list(map(int, s[0].split(",")))

def finished(used):
    for i in range(5):
        all = True
        for j in range(5):
            if not used[i][j]:
                all = False
        if all:
            return True

    for j in range(5):
        all = True
        for i in range(5):
            if not used[i][j]:
                all = False
        if all:
            return True

    return False

def score(board, used):
    sum = 0
    for i in range(5):
        for j in range(5):
            if not used[i][j]:
                sum += board[i][j]
    return sum


best_score = 0
earliest = 1000
for board_index in range(2, len(s), 6):
    used = []
    for i in range(5):
        used.append([False] * 5)
    board = []
    for i in range(5):
        board.append(list(
            map(int, filter(lambda x: len(x) > 0, s[board_index + i].split(" ")))
        ))
    for k in range(len(nums)):
        num = nums[k]
        for i in range(5):
            for j in range(5):
                if board[i][j] == num:
                    used[i][j] = True
        if finished(used):
            board_score = score(board, used) * num
            if earliest > k:
                earliest = k
                best_score = board_score
            if earliest == k and best_score < board_score:
                best_score = board_score
            break

print(best_score)
