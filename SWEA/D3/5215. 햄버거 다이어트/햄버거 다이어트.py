TC = int(input())
for tc in range(1, TC + 1):
    N, L = map(int, input().split())
    items = [map(int, input().split()) for _ in range(N)]

    dp = [0] * (L + 1)

    for score, cal in items:
        for c in range(L, cal - 1, -1):
            dp[c] = max(dp[c], dp[c - cal] + score)

    print(f"#{tc} {max(dp)}")
