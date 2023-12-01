import sys

n, q = map(int, sys.stdin.readline().split())
visited = set()
for i in range(q):
  v = int(sys.stdin.readline())
  result = 0
  r = v
  while r > 0:
    if r in visited:
      result = r
    r = r // 2
  visited.add(v)
  print(result)
