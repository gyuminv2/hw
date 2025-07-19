TC = int(input())
for t in range(1, TC + 1):
    target = input()
    prev = '0'
    rtn = 0
    for bit in target:
        if bit != prev:
            rtn += 1
            prev = bit
    print(f'#{t} {rtn}')
