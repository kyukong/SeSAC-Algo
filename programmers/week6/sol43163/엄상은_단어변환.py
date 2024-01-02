from collections import deque

def bfs(words, target, changeable, q):
    visited = [0 for _ in range(len(changeable))]
    while q:
        index, depth = q.popleft()
        if visited[index] == 0:
            visited[index] = 1
            if (words[index] == target):
                return depth
            for j in range(len(changeable[index])):
                if changeable[index][j] == 1 and visited[j] == 0:
                    q.append([j, depth + 1])
    return 0

def solution(begin, target, words):
    w_count = len(words)
    changeable = [[0 for _ in range(w_count)] for _ in range(w_count)]

    # begin에서 words[i]로 변환 가능한지 확인
    q = deque()
    for i in range(w_count):
        b_match = 0
        for k in range(len(words[i])):
            if words[i][k] == begin[k]:
                continue
            else:
                b_match += 1        
        if b_match == 1:
            q.append([i, 1]) # depth 함께 넣어주기

    # words[i]가 words[j]로 변환 가능한지 확인
    for i in range(w_count):
        for j in range(w_count):
            match = 0           
            for k in range(len(words[i])):
                if words[i][k] == words[j][k]:
                    continue
                else:
                    match += 1
            if match == 1:
                changeable[i][j] = 1
    answer = bfs(words, target, changeable, q)
    return answer