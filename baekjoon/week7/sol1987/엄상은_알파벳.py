import sys

R, C = map(int, sys.stdin.readline().split())
board = [['' for _ in range(C)] for _ in range(R)]
for i in range(R):
  board[i] = list(sys.stdin.readline().strip())
max_count = 0

def dfs(x, y, visited, depth):
  global max_count
  dx = [0, -1, 0, 1]
  dy = [1, 0, -1, 0]
  max_count = max(depth, max_count)
  for d in range(4):
    if 0 <= x+dx[d] < len(board) and 0 <= y+dy[d] < len(board[0]) and not visited[ord(board[x+dx[d]][y+dy[d]])-65]:
      visited[ord(board[x+dx[d]][y+dy[d]])-65] = True
      dfs(x+dx[d], y+dy[d], visited, depth+1)
      visited[ord(board[x+dx[d]][y+dy[d]])-65] = False

visited = [False for _ in range(26)]
visited[ord(board[0][0])-65] = True
dfs(0, 0, visited, 1)
print(max_count)