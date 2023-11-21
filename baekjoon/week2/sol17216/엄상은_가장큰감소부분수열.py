import sys

n = int(input())
l = list(map(int, sys.stdin.readline().split()))
dp = [0 for _ in range(n)]

# dp[i]: i가 포함된 감소수열 중 가장 큰 합..
# dp[i] 구하려면 앞에 애들이 계속 감소하는지 보면서 나랑 크기비교도 해야 함

for i in range(n):
  dp[i] = l[i]
  for j in range(i):
    if (l[j] > l[i]):  # 현재 인덱스가 dp[j]까지 감소수열 부분에 포함될 수 있을 때
      dp[i] = max(dp[j]+l[i], dp[i])

print(max(dp))