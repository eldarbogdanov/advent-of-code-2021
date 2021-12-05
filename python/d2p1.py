import sys

x = 0
y = 0
with open("test.in") as f:
    lines = f.readlines()
    for line in lines:
        a = line.split(" ")[0]
        b = int(line.split(" ")[1])
        if a == "forward":
            x += b
        if a == "down":
            y += b
        if a == "up":
            y -= b

print(x * y)

