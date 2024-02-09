import sys
from collections import deque

# 같은 색의 뿌요가 4개 모이면 터짐
# 여러 그룹이 터져도 한 번의 연쇄
# 터지고 나서 밑으로 떨어짐
# 몇 번의 연쇄 일어나는지 구하기

puyo = [list(sys.stdin.readline().strip()) for _ in range(12)]

def bfs(x, y):
    global visited
    global puyo
    q = deque([(x, y)])
    d = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    same_color = []
    
    while q:
        i, j = q.popleft()
        if visited[i][j] == 1:
            continue
        visited[i][j] = 1
        same_color.append((i, j))
        for m in range(4):
            dx, dy = d[m]
            n_i = i + dx
            n_j = j + dy
            if 0 <= n_i < 12 and 0 <= n_j < 6 and puyo[n_i][n_j] == puyo[i][j] and visited[n_i][n_j] == 0:
                q.append((n_i, n_j))

    if len(same_color) >= 4:
        for s in same_color:
            i, j = s
            puyo[i][j] = '.'
        return True
    return False

answer = 0
while True:
    # 네 개 이상 모이면 .으로 바꾸기
    visited = [[0 for _ in range(6)] for _ in range(12)]
    is_worked = False
    for i in range(12):
        for j in range(6):
            if puyo[i][j] != '.':
                if bfs(i, j):
                    is_worked = True
    if not is_worked:
        break
    answer += 1
    # 밑으로 떨어지게 만들기
    for j in range(6):
        left = []
        for i in range(12):
            if puyo[i][j] != '.':
                left.append(puyo[i][j])
        for l in range(12):
            if l < 12-len(left):
                puyo[l][j] = '.'
            else:
                puyo[l][j] = left[l-12+len(left)]
        
print(answer)