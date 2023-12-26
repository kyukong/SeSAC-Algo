def find(i, network):
    if (network[i] != i):
        network[i] = find(network[i], network)
    return network[i]

def union(i, j, network):    
    network[max(i, j)] = min(i, j)

def solution(n, computers):
    network = [i for i in range(n)]
    for i in range(n):
        for j in range(n):
            if (computers[i][j] == 1) and (i != j):
                # 둘의 대표 노드 찾기
                n_i = find(i, network)
                n_j = find(j, network)
                # 둘 연결하기
                union(n_i, n_j, network)
    for i in range(n):
        find(i, network)
    n_set = set(network)
    answer = len(n_set)
    return answer