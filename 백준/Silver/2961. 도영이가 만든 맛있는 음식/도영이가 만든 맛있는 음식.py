N = int(input())
lst = [tuple(map(int, input().split())) for _ in range(N)]

def dfs(idx, total_sin, total_ssun, pick):
    global min_score

    if idx == N:
        if pick:
            min_score = min(min_score, abs(total_sin - total_ssun))
        return

    dfs(idx+1, total_sin * lst[idx][0], total_ssun + lst[idx][1], 1)
    dfs(idx+1, total_sin, total_ssun, pick)


min_score = 987654321
dfs(0, 1, 0, 0) # idx, total_sin, total_ssun
print(min_score)