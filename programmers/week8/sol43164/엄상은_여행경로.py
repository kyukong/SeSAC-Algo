def dfs(start, tickets, used, route, answer):
    if 0 not in used:
        answer.append(route)
    for i in range(len(tickets)):
        if used[i] == 0 and tickets[i][0] == start:
            used[i] = 1
            route.append(tickets[i][1])
            dfs(tickets[i][1], tickets, used, route[:], answer)
            used[i] = 0
            route.pop()

def solution(tickets):
    answer = []
    used = [0 for _ in range(len(tickets))]
    dfs("ICN", tickets, used, ["ICN"], answer)
    return sorted(answer)[0]