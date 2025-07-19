T = int(input())
for test_case in range(1, T + 1):
    arr = list(map(int, input().split()))
    rtn = 0
    for v in arr:
        rtn += v if v % 2 != 0 else 0
    print(f'#{test_case} {rtn}')