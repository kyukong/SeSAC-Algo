import heapq

def solution(prices):
    answer = [0 for _ in range(len(prices))]
    q = []
    
    for i in range(len(prices)):
        heapq.heappush(q, (- prices[i], i))
        while True:
            h_p, h_i = heapq.heappop(q)
            h_p = -h_p
            if h_p > prices[i]:
                answer[h_i] = i - h_i
            else:
                heapq.heappush(q, (-h_p, h_i))
                break
    for _ in range(len(q)):
        h_p, h_i = heapq.heappop(q)
        answer[h_i] = len(prices)-1-h_i
    return answer