import sys
input = sys.stdin.readline

n = int(input())
san = list(map(int, input().split()))
m = int(input())
test = list(map(int, input().split()))

d = dict()
for i in range(len(san)):
    d[san[i]] = 0

for t in test:
    if t in d:
        print(1, end=' ')
    else:
        print('0', end=' ')