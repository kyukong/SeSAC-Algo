import sys

INF = int(1e9)

n, m = map(int, sys.stdin.readline().split())
routes = []
for _ in range (m):
  s, e, time = map(int, sys.stdin.readline().split())
  routes.append((s, e, time))

distance = [INF] * (n + 1)
distance[0] = 0 # 이용하지 않을 부분
distance[1] = 0 # 시작점
impossible = False

for i in range (n): # 확인할 노드 선택 (마지막 한 번은 루프 확인을 위함)
  for j in range (m): # 간선 전체를 실행
    s = routes[j][0]
    e = routes[j][1]
    time = routes[j][2]
    if (distance[s] != INF and distance[e] > distance[s] + time):
      distance[e] = distance[s] + time
      if (i == n-1):
        impossible = True
        break

if (impossible):
  print(-1)
else:
  for i in range(2, n+1):
    if (distance[i] == INF):
      print(-1)
    else:
      print(distance[i])