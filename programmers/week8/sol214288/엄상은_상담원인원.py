import itertools
import heapq
import math

def solution(k, n, reqs):
  answer = math.inf
  if k == 1:
    mento_status = [0 for _ in range(n)]
    w = 0
    for r in reqs:
      min_time = heapq.heappop(mento_status)
      if r[0] < min_time:
        w += min_time-r[0]
        heapq.heappush(mento_status, min_time+r[1])
      else:
        heapq.heappush(mento_status, r[0]+r[1])
    answer = w
  else:
    for combi in itertools.combinations(range(1, n), k-1):
      w = 0
      mento_status = [[] for _ in range(k+1)] # 종료 시간이 들어감
      for i in range(1, k+1):
        if i == 1:
          mento_status[i] = [0 for _ in range(combi[0])]
        elif i == k:
          mento_status[i] = [0 for _ in range(n-combi[k-2])]
        else:
          mento_status[i] = [0 for _ in range(combi[i-1]-combi[i-2])]
      for r in reqs:
        min_time = heapq.heappop(mento_status[r[2]])
        if r[0] < min_time:
          w += min_time-r[0]
          heapq.heappush(mento_status[r[2]], min_time+r[1])
        else:
          heapq.heappush(mento_status[r[2]], r[0]+r[1])
      answer = min(answer, w)
  return answer