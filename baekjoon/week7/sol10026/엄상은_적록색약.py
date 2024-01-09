import sys
sys.setrecursionlimit(10**7)

def dfs(x, y):
  dx = [-1, 0, 1, 0]
  dy = [0, 1, 0, -1]
  if visited[x][y] == 0:
    visited[x][y] = 1
    for i in range(4):
      if 0 <= x+dx[i] < N and 0 <= y+dy[i] < N and visited[x+dx[i]][y+dy[i]] == 0 and art[x][y] == art[x+dx[i]][y+dy[i]]:
        dfs(x+dx[i], y+dy[i])

N = int(input())
art = []
for _ in range(N):
  art.append(sys.stdin.readline().split()[0])
visited = [[0 for _ in range(N)] for _ in range(N)]
count_1 = 0
count_2 = 0

for i in range(N):
  for j in range(N):
    if visited[i][j] == 0:
      dfs(i, j)
      count_1 += 1

visited = [[0 for _ in range(N)] for _ in range(N)]
for i in range(N):
  for j in range(N):
    if art[i][j] == 'R':
      art[i] = art[i].replace('R', 'G')
for i in range(N):
  for j in range(N):
    if visited[i][j] == 0:
      dfs(i, j)
      count_2 += 1

print(count_1, count_2)