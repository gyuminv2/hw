# 맥주 1 박스 = 20 병
# 50미터 = 1 병
# 편의점 = 20 병

from collections import deque

t = int(input())

def bfs():
    q = deque([(home_x, home_y)])
    
    while q:
        x, y = q.popleft()
        if abs(x - kanye_x) + abs(y - kanye_y) <= 1000:
            print('happy')
            return
        for i in range(n):
            if not visited[i]:
                nx, ny = gs25[i]
                if abs(x-nx) + abs(y-ny) <= 1000:
                    visited[i] = 1
                    q.append([nx, ny])

    print('sad')
                

for _ in range(t):
    n = int(input())
    home_x, home_y = map(int, input().split())
    gs25 = [
        list(map(int, input().split()))
        for _ in range(n)
    ]
    kanye_x, kanye_y = map(int, input().split())
    visited = [0] * (n+1)
    bfs()