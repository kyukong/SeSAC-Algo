import sys

n, k = map(int, sys.stdin.readline().split())
dp = [[0 for _ in range(k+1)] for _ in range (n+1)]

max_value = 0
for i in range(1, n+1):
  w, v = map(int, sys.stdin.readline().split())
  for j in range(1, k+1):
    if (j >= w):
      dp[i][j] = max(dp[i-1][j], dp[i-1][j-w] + v)
    else:
      dp[i][j] = dp[i-1][j]
    max_value = max(dp[i][j], max_value)

print(max_value)