import sys
import heapq
import math

# 이동: 1부터 n까지
n, m = map(int, sys.stdin.readline().split())
# graph 관계 인풋 받기
graph = [[] for _ in range(n+1)]

for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[a].append((c, b))
    graph[b].append((c, a))

# q는 최소 여물이 갱신되었을 경우 넣어짐
q = []
# 1부터 index까지 필요한 여물의 수 저장
count = [math.inf] * (n+1)

heapq.heappush(q, (0, 1))
count[1] = 0
while q:
    cur = heapq.heappop(q)[1]
    # cur에서 i까지 계산
    for g in graph[cur]:
        d, e = g
        if count[cur] + d < count[e]:
            count[e] = count[cur] + d
            heapq.heappush(q, (count[e], e)) # 여물 수와 인덱스 저장

print(count[n])