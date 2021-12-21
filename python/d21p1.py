import sys

with open("../test.in") as f:
    nums = f.read().splitlines()

ways = []
for i in range(22):
    ways[i] = [0] * 22

