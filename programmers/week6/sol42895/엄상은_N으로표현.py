import math
import sys

sys.setrecursionlimit(10**7)

def dp(count, index, value, N):
    if (0 < index < len(count)) and (value <= 8):
        count[index] = min(value, count[index])
        if (count[index] == value):
            for i in range(1, 9):
                # N + 10 * N + 10^2 * N + ... + 10^(i-1) * N
                new_index = 0
                for j in range(0, i):
                    new_index += N * pow(10, j)
                dp(count, index + new_index, value + i, N)
                dp(count, index * new_index, value + i, N)
                dp(count, int(index / new_index), value + i, N)
                dp(count, int(new_index / index), value + i, N)
                dp(count, index - new_index, value + i, N)
                dp(count, new_index - index, value + i, N)

def solution(N, number):
    count = [math.inf for i in range(99999999)]
    count[N] = 1
    dp(count, N, 1, N)

    # 기본 초기화
    for i in range(1, 9):
        # N + 10 * N + 10^2 * N + ... + 10^(i-1) * N
        index = 0
        for j in range(0, i):
            index += N * pow(10, j)
        dp(count, index, i, N)

    if (count[number] <= 8):
        answer = count[number]
    else:
        answer = -1
    return answer