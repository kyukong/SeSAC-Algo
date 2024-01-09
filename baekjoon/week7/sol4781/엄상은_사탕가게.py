import sys

while True:
  temp = sys.stdin.readline().split()
  n = int(temp[0])
  m = int(float(temp[1]) * 100 + 0.5)
  if n == 0 and m == 0:
    break
  candy = [[0, 0] for _ in range(n)]
  for i in range(n):
    c, p = list(map(float, sys.stdin.readline().split()))
    candy[i][0] = int(c)
    candy[i][1] = int(p * 100 + 0.5)

  max_cal = [0 for _ in range(m+1)] # (index)원을 가지고 살 수 있는 최대 칼로리
  for i in range(1, m+1):
    for j in range(0, n):
      if i-candy[j][1] >= 0:
        max_cal[i] = max(max_cal[i], max_cal[i-candy[j][1]] + candy[j][0])
  print(max_cal[m])