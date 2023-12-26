n = int(input())
c = []
dp = [1 for _ in range(n)]
for i in range(n):
  c.append(int(input()))
  for j in range(i):
    if (c[j] < c[i]):
      dp[i] = max(dp[j]+1, dp[i])
print(n - max(dp))