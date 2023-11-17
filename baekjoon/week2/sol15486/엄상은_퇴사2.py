import sys

n = int(sys.stdin.readline())
dp = [0 for _ in range(n+1)]

for i in range(1, n+1):
  rl = sys.stdin.readline()
  day, benefit = map(int,rl.split())
  dp[i] = max(dp[i], dp[i-1])
  if (i+day-1 <= n):
    dp[i+day-1] = max(dp[i+day-1], dp[i-1]+benefit)

# print(dp)
print(dp[n])