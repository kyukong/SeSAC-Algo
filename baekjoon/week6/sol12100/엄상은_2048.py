import sys, copy
import itertools

n = int(sys.stdin.readline())
board = [[0 for _ in range(n)] for _ in range(n)]
for i in range(n):
  board[i] = list(map(int, sys.stdin.readline().split()))
max_value = 0
orig_board = board

for d_order in itertools.product([0, 1, 2, 3], [0, 1, 2, 3], [0, 1, 2, 3], [0, 1, 2, 3], [0, 1, 2, 3]):
  board = copy.deepcopy(orig_board)
  for d in d_order:
    if d == 0: 
      for i in range(n):
        temp = []
        j = -1
        while j < n - 1:
          j += 1
          if board[j][i] == 0:
            continue
          elif j == n-1:
            temp.append(board[j][i])
            break
          for k in range(1, n-j):
            if board[j+k][i] == 0:
              if j+k == n-1:
                temp.append(board[j][i])
              else:
                continue
            elif board[j][i] == board[j+k][i]:
              temp.append(board[j][i] * 2)
              j += k
              break
            else:
              temp.append(board[j][i])
              j += k - 1
              break
        # board[i]를 temp로 교체
        zero_count = n - len(temp)
        for _ in range(zero_count):
          temp.append(0)
        for t_i in range(len(temp)):
          board[t_i][i] = temp[t_i]
    
    elif d == 2:
      for i in range(n):
        temp = []
        j = n
        while j > 0:
          j -= 1
          if board[j][i] == 0:
            continue
          elif j == 0:
            temp.append(board[j][i])
            break
          for k in range(1, j + 1):
            if board[j-k][i] == 0:
              if j-k == 0:
                temp.append(board[j][i])
              else:
                continue
            elif board[j][i] == board[j-k][i]:
              temp.append(board[j][i] * 2)
              j -= k
              break
            else:
              temp.append(board[j][i])
              j -= k - 1
              break
        # board[i]를 temp로 교체
        zero_count = n - len(temp)
        for _ in range(zero_count):
          temp.append(0)
        temp = temp[::-1]
        for t_i in range(len(temp)):
          board[t_i][i] = temp[t_i]

    elif d == 1:
      for i in range(n):
        temp = []
        j = n
        while j > 0:
          j -= 1
          if board[i][j] == 0:
            continue
          elif j == 0:
            temp.append(board[i][j])
            break
          for k in range(1, j + 1):
            if board[i][j-k] == 0:
              if j-k == 0:
                temp.append(board[i][j])
              else:
                continue
            elif board[i][j] == board[i][j-k]:
              temp.append(board[i][j] * 2)
              j -= k
              break
            else:
              temp.append(board[i][j])
              j -= k - 1
              break
        # board[i]를 temp로 교체
        zero_count = n - len(temp)
        for _ in range(zero_count):
          temp.append(0)
        board[i] = temp[::-1]
    
    elif d == 3:
      for i in range(n):
        temp = []
        j = -1
        while j < n - 1:
          j += 1
          if board[i][j] == 0:
            continue
          elif j == n-1:
            temp.append(board[i][j])
            break
          for k in range(1, n-j):
            if board[i][j+k] == 0:
              if j+k == n-1:
                temp.append(board[i][j])
              else:
                continue
            elif board[i][j] == board[i][j+k]:
              temp.append(board[i][j] * 2)
              j += k
              break
            else:
              temp.append(board[i][j])
              j += k - 1
              break
        # board[i]를 temp로 교체
        zero_count = n - len(temp)
        for _ in range(zero_count):
          temp.append(0)
        board[i] = temp
  for i in range(n):
    for j in range(n):
      max_value = max(max_value, board[i][j])

print(max_value)