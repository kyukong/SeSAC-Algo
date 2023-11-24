import sys

def is_movable(x, y, fish):
  if not (0 <= x < 4 and 0 <= y < 4):
    return False
  elif (fish[x][y][0] == -1):
    return False
  return True

fish = [[[0, 0] for _ in range(4)] for _ in range(4)] # 물고기 공간
position = [[0, 0] for _ in range(17)] # 물고기의 좌표

for i in range(4):
  line = sys.stdin.readline().split()
  for j in range(4):
    fish[i][j] = [int(line[j * 2]), int(line[j * 2 + 1])]
    position[int(line[j * 2])] = [i, j]
fish[0][0][0] = -1 # 상어 위치
position[7] = [-1, -1]

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

for i in range(1, 17):
  fishX = position[i][0]
  fishY = position[i][1]
  if (fishX == -1) or (fishY == -1):
    continue
  m = fish[fishX][fishY][1] - 1 # 이동해야하는 dx, dy 인덱스
  for j in range(8):
    newX = fishX + dx[m]
    newY = fishY + dy[m]
    if (is_movable(newX, newY, fish)):
      old = fish[fishX][fishY]
      new = fish[newX][newY]
      # fish 업데이트
      fish[fishX][fishY] = new
      fish[newX][newY] = [old[0], m + 1]
      # position 업데이트
      position[old[0]] = [newX, newY]
      position[new[0]] = [fishX, fishY]
      break
    else:
      m += 1
      if (m == 8):
        m = 0

print(fish)
print(position)