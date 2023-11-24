import sys

def find(x, parent):
  if (parent[x] == x):
    return x
  else:
    parent[x] = find(parent[x], parent)
    return parent[x]

def union(a, b, parent):
  if (find(a, parent) == find(b, parent)):
    return False
  else:
    min_v = min(find(a, parent), find(b, parent))
    max_v = max(find(a, parent), find(b, parent))
    parent[max_v] = min_v
    return True

# 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리

v, e = map(int, sys.stdin.readline().split())
graph = []
for i in range (e):
  a, b, c = map(int, sys.stdin.readline().split())
  graph.append((c, a, b))

# c의 값이 작은 것을 순서대로 정렬
graph.sort()

sum = 0
parent = [i for i in range(v+1)]
# 작은 것부터 하나씩 넣으면서(union) 사이클이 생기지 않도록 추가
# 대표노드: 값이 작은 것
for g in graph:
  c = g[0]
  a = g[1]
  b = g[2]
  if (union(a, b, parent)):
    sum += c
print(sum)