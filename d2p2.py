import sys

x = 0
y = 0
aim = 0
with open("d1.in") as f:
    lines = f.readlines()
    for line in lines:
        a = line.split(" ")[0]
        b = int(line.split(" ")[1])
        if a == "forward":
            x += b
            y += aim * b
        if a == "down":
            aim += b
        if a == "up":
            aim -= b

print(x * y)
