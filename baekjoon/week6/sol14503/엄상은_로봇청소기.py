import sys

N, M = map(int, sys.stdin.readline().split())
r, c, d = map(int, sys.stdin.readline().split())
area = [[0 for _ in range(M)] for _ in range(N)]

for i in range(N):
  area[i] = list(map(int, sys.stdin.readline().split()))

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

count = 0
while True:
  if (area[r][c] == 0):
    count += 1
    area[r][c] = 2
  t_count = 0
  for _ in range(4):
    # 전진
    d = 3 if d == 0 else d - 1 # 반시계 방향으로 90도 회전
    if area[r + dx[d]][c + dy[d]] == 0:
      r = r + dx[d]
      c = c + dy[d]
      break
    t_count += 1
  # 후진
  if t_count == 4:
    if area[r - dx[d]][c - dy[d]] != 1:
      r = r - dx[d]
      c = c - dy[d]
    else:
      break

print(count)