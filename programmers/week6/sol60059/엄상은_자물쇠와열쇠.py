def rotate_90(key):
    new_key = [[-1 for _ in range(len(key))] for _ in range(len(key))]
    for i in range(len(key)):
        for j in range(len(key)):
            new_key[i][j] = key[len(key)-1-j][i]
    return new_key

def solution(key, lock):
    m = len(key)
    n = len(lock)
    for _ in range(4): # 회전
        for ki in range(-m+1, n+m):
            for kj in range(-m+1, n+m):                
                answer_here = True
                for i in range(n):
                    for j in range(n):
                        if 0 <= i-ki < m and 0 <= j-kj < m: 
                            if lock[i][j] + key[i-ki][j-kj] != 1:
                                answer_here = False
                                break
                        elif lock[i][j] == 0:
                            answer_here = False
                            break
                if answer_here:
                    return True
        key = rotate_90(key)
    return False