def dfs(i, t_score, t_kcal):
    global m_score
    if t_kcal > L:
        return
    if i == N:
        if t_score > m_score:
            m_score = t_score
        return
    dfs(i + 1, t_score + lst[i][0], t_kcal + lst[i][1])
    dfs(i + 1, t_score, t_kcal)
TC = int(input())
for tc in range(1, TC + 1):
    N, L = map(int, input().split())
    lst = [list(map(int, input().split())) for _ in range(N)]
    m_score = 0
    dfs(0, 0, 0)
    print(f"#{tc} {m_score}")