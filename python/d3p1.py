import sys

with open("test.in") as f:
  strings = f.read().splitlines()

l = len(strings[0])
n = len(strings)
a = 0
b = 0
for i in range(l):
  zero = 0
  one = 0
  for j in range(n):
    if strings[j][i] == '0':
      zero += 1
    else:
      one += 1
  if (one >= zero):
    a += pow(2, l - i - 1)
  else:
    b += pow(2, l - i - 1)

print(a * b)
