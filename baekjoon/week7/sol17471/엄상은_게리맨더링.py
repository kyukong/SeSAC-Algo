import sys
import itertools
from collections import deque

def is_connected(adj, area, team_x, n): # team_x는 team_1 or team_2
  # area가 타고 올라가서 team_x[0]이 될 때까지 
  if area == team_x[0]:
    return True
  q = deque([area])
  visited = [0 for _ in range(n)]
  while q:
    a = q.popleft()
    for ad in adj[a]:
      if visited[ad] == 0:
        if ad == team_x[0]:
          return True
        if ad in team_x:
          q.append(ad)
        visited[ad] = 1
  return False

n = int(sys.stdin.readline())
popul = list(map(int, sys.stdin.readline().split()))
adj = [list(map(lambda x: int(x) - 1, sys.stdin.readline().split()))[1:] for _ in range(n)]

diff = 1000
for team in itertools.product([1, 2], repeat=n):
  if 1 not in team or 2 not in team:
    continue
  team_1 = []
  team_2 = []
  for i in range(n):
    if team[i] == 1:
      team_1.append(i)
    else:
      team_2.append(i)
  
  is_avail = True
  popul_1 = 0
  popul_2 = 0
  for area in team_1:
    popul_1 += popul[area]
    if not is_connected(adj, area, team_1, n):
      is_avail = False
      break
  if is_avail:
    for area in team_2:
      popul_2 += popul[area]
      if not is_connected(adj, area, team_2, n):
        is_avail = False
        break

  if is_avail:
    diff = min(abs(popul_1 - popul_2), diff)
if diff == 1000:
  print(-1)
else:
  print(diff)