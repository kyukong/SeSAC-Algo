n = int(input())
board = [[0 for _ in range(n)] for _ in range(n)]
for i in range(n):
  board[i] = list(map(int, input().split()))

answer = [[0 for _ in range(n+10)] for _ in range(n+10)]

# (i, n-1) (n-1, i) 초기화
for i in range(0, n-1):
  if (board[i][n-1] + i == n-1):
    answer[i][n-1] += 1
  if (board[n-1][i] + i == n-1):
    answer[n-1][i] += 1

for i in range(1, n):
  for j in range(n-i, -1, -1):
    for k in range(n-i, -1, -1):
      if (answer[j][k] == 0):
        answer[j][k] = answer[j+board[j][k]][k] + answer[j][k+board[j][k]]
      if (answer[k][j] == 0):
        answer[k][j] = answer[k+board[k][j]][j] + answer[k][j+board[k][j]]

print(answer[0][0])