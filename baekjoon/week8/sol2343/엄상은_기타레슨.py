import sys

n, m = map(int, sys.stdin.readline().split())
long = list(map(int, sys.stdin.readline().split()))

def find(low, high):
    global long
    global m
    if low == high:
        return low
    mid = (low + high) // 2
    l_sum = 0
    l_count = 1
    for l in long:
        if l_sum + l <= mid:
            l_sum += l
        else:
            l_sum = l
            l_count += 1
    # 최소를 찾기 위한 것. mid에서 통과했을 때 low인 것을 찾아야 함
    if l_count <= m:
        return find(low, mid)
    elif l_count > m:
        return find(mid+1, high)

# 답이 될 부분(블루레이의 크기 중 최소)을 이분탐색
low = max(long)
high = sum(long)
print(find(low, high))