import sys

def is_bingo(board, player):
  if (board[0][0] == board[0][1] == board[0][2] == player):
    return True
  elif (board[1][0] == board[1][1] == board[1][2] == player):
    return True
  elif (board[2][0] == board[2][1] == board[2][2] == player):
    return True
  elif (board[0][0] == board[1][0] == board[2][0] == player):
    return True
  elif (board[0][1] == board[1][1] == board[2][1] == player):
    return True
  elif (board[0][2] == board[1][2] == board[2][2] == player):
    return True
  elif (board[0][0] == board[1][1] == board[2][2] == player):
    return True
  elif (board[0][2] == board[1][1] == board[2][0] == player):
    return True
  return False

while True:
  rl = sys.stdin.readline()
  answer = 'valid'
  if (rl == 'end\n'):
    break
  board = [[0 for _ in range(3)] for _ in range(3)]
  b_status = 1 # full
  x_count = 0
  o_count = 0
  for i in range(3):
    for j in range(3):
      board[i][j] = rl[i * 3 + j]
      if (board[i][j] == '.'):
        b_status = 0 # not full
      elif (board[i][j] == 'X'):
        x_count += 1
      else:
        o_count += 1

  if (b_status == 1): # full
    if ((x_count != 5) or (o_count != 4)):
      answer = 'invalid'
    else: # 한 줄은 x의 것
      if (is_bingo(board, 'O')):
        answer = 'invalid'
  else: # not full
    if (x_count == o_count): # 한 줄은 o의 것
      if (is_bingo(board, 'X')):
        answer = 'invalid'
      if not (is_bingo(board, 'O')):
        answer = 'invalid'
    elif (x_count == o_count+1): # 한 줄은 x의 것
      if (is_bingo(board, 'O')):
        answer = 'invalid'
      if not (is_bingo(board, 'X')):
        answer = 'invalid'
    else:
      answer = 'invalid'
  print(answer)
