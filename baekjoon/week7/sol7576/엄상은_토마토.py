import sys
from collections import deque

m, n = map(int, sys.stdin.readline().split())
box = []
for i in range(n):
  temp = list(map(int, sys.stdin.readline().split()))
  box.append(temp)

q = deque()
for i in range(n):
  for j in range(m):
    if box[i][j] == 1:
      q.append((i, j))

d = [(0, 1), (1, 0), (0, -1), (-1, 0)]
while q:
  x, y = q.popleft()
  for i in range(4):
    n_x = x + d[i][0]
    n_y = y + d[i][1]
    if 0 <= n_x < n and 0 <= n_y < m and box[n_x][n_y] == 0:
      q.append((n_x, n_y))
      box[n_x][n_y] = box[x][y] + 1

days = 0
unable = False
for i in range(n):
  for j in range(m):
    days = max(days, box[i][j] - 1)
    if box[i][j] == 0:
      unable = True
      break

if not unable:
  print(days)
else:
  print(-1)