def solution(triangle):
    depth = len(triangle)
    result = [[0 for _ in range(depth)] for _ in range(depth)]
    result[0][0] = triangle[0][0]
    for i in range(1, depth):
        for j in range(i+1):
            left = result[i-1][j] + triangle[i][j]
            right = result[i-1][j-1] + triangle[i][j]
            if (j == 0):
                result[i][j] = left
            elif (j == i):
                result[i][j] = right
            else:
                result[i][j] = max(left, right)    
    answer = max(result[depth-1])
    return answer