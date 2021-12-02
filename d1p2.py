import sys

ans = 0
count = 0
prev = 10000
nums = None

with open(sys.argv[1]) as f:
  nums = list(map(int, f.readlines()))

for i in range(2, len(nums)):
  cur = nums[i - 2] + nums[i - 1] + nums[i]
  #print(cur)
  if cur > prev:
    ans += 1
  prev = cur

print(ans)
