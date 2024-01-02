from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    q = deque([[n-1, m-1, 1]]) # 1은 지나친 칸수
    
    directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    
    while q:
        r, c, d = q.popleft()        
        if r == 0 and c == 0:
            return d
        elif (maps[r][c] == 1):
            maps[r][c] = 2 # 방문한 자리
            for i in range(4):
                new_r = r + directions[i][0]
                new_c = c + directions[i][1]
                if 0 <= new_r < n and 0 <= new_c < m and maps[new_r][new_c] == 1:
                    q.append([new_r, new_c, d + 1])
    return -1