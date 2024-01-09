import sys

n, k = map(int, sys.stdin.readline().split())
coins = [0]
for _ in range(n):
  coins.append(int(sys.stdin.readline()))

count = [10001 for _ in range(k+1)]
count[0] = 0
for i in range(1, k+1):
  for j in range(1, n+1):
    if i-coins[j] >= 0:
      count[i] = min(count[i], count[i-coins[j]] + 1)

# count[k] 중 최소 구하기
# print(count)

if count[k] == 10001:
  print(-1)
else:
  print(count[k])