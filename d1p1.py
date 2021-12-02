import sys

ans = 0
prev = 10000
with open(sys.argv[1]) as f:
  lines = f.readlines()
  for line in lines:
    cur = int(line)
    if cur > prev:
      ans += 1
    prev = cur

print(ans)
