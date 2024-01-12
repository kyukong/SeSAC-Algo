import sys

n = int(sys.stdin.readline())
cost = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
visited = [0 for _ in range(n)]

def dfs(start, index, visited, cost, cost_now):
  global min_dist
  if 0 not in visited:
    if cost[index][start] != 0:
      min_dist = min(min_dist, cost_now + cost[index][start])
  else:
    for j in range(n):
      if cost[index][j] == 0 or visited[j] == 1:
        continue
      visited[j] = 1
      dfs(start, j, visited, cost, cost_now + cost[index][j])
      visited[j] = 0

global min_dist 
min_dist = 10000001
for i in range(n):
  visited[i] = 1
  dfs(i, i, visited, cost, 0)
  visited[i] = 0

print(min_dist)