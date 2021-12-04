import sys

with open("test.in") as f:
  s = f.read().splitlines()

l = len(s[0])
n = len(s)
ans = [0, 0]
for solution_index in range(2):
  left = [True] * n
  count = n
  for position in range(l):
    zero = 0
    one = 0
    for i in range(n):
      if left[i]:
        if s[i][position] == '0':
          zero += 1
        else:
          one += 1

    good = None
    if solution_index == 0:
      good = '1' if one >= zero else '0'
    else:
      good = '0' if zero <= one else '1'

    for i in range(n):
      if s[i][position] != good and left[i]:
        left[i] = False
        count -= 1

    if count == 1:
      for i in range(n):
        if left[i]:
          ans[solution_index] = int(s[i], 2)

print(ans[0] * ans[1])
