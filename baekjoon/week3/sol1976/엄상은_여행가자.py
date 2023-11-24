import sys

def find(a, parents):
  if (parents[a] == a):
    return a
  else:
    parents[a] = find(parents[a], parents)
    return parents[a]

n = int(input())
m = int(input())
parents = [i for i in range(n+1)]
plan = []

for i in range(n):
  line = list(map(int, sys.stdin.readline().split()))
  for j in range(i):
    if (line[j] == 1):
      max_v = max(find(i+1, parents), find(j+1, parents))
      min_v = min(find(i+1, parents), find(j+1, parents))
      parents[max_v] = min_v
      
plan = list(map(int, sys.stdin.readline().split()))

is_available = True
for i in range(m-1):
  if (find(plan[i], parents) != find(plan[i+1], parents)):
    is_available = False

if (is_available):
  print('YES')
else:
  print('NO')
# print(parents)