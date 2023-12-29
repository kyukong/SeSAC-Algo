import sys
from collections import deque

H, W = map(int, sys.stdin.readline().split())
cheese = [[0 for _ in range(W)] for _ in range(H)]
for i in range(H):
  cheese[i] = list(map(int, sys.stdin.readline().split()))

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

hour = 0
left_count = 0

while True:
  cheese_edge = []
  visited = [[0 for _ in range(W)] for _ in range(H)]
  outer_q = deque()
  for i in range(H):
    for j in range(W):
      if i == 0 or i == H - 1 or j == 0 or j == W - 1:
        outer_q.append([i, j])
  while outer_q:
    i, j = outer_q.popleft()
    if visited[i][j] == 0 and cheese[i][j] == 0:
      visited[i][j] = 1
      for d in range(4):
        next_x = i + dx[d]
        next_y = j + dy[d]
        if not (0 <= next_x < H and 0 <= next_y < W):
          continue
        if cheese[next_x][next_y] == 0:
          outer_q.append([next_x, next_y])
        elif [next_x, next_y, 1] not in cheese_edge:
          cheese_edge.append([next_x, next_y])
          cheese[next_x][next_y] = 0
          visited[next_x][next_y] = 1

  if len(cheese_edge) == 0:
    print(hour)
    print(left_count)
    break
  else:
    hour += 1
    left_count = len(cheese_edge)
