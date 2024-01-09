import sys

def get_sum(rect):
  result = 0
  for i in range(len(rect)):
    for j in range(len(rect[0])):
      result += rect[i][j]
  return result

def get_prod(rect1, rect2):
  prod =  get_sum(rect1) * get_sum(rect2)
  return prod

def divide_rect(rect, level): # level은 1 또는 2
  # 반으로 나눠
  h = len(rect)
  w = len(rect[0])
  
  max_result = 0
  for l1 in range(1, h):
    rect1 = rect[:l1]
    rect2 = rect[l1:]
    if level == 1:
      max_result = max(divide_rect(rect1, 2) * get_sum(rect2), max_result)
      max_result = max(divide_rect(rect2, 2) * get_sum(rect1), max_result)
    else:
      max_result = max(get_sum(rect1) * get_sum(rect2), max_result)

  for l2 in range(1, w):
    rect1 = [[0 for _ in range(l2)] for _ in range(h)]
    rect2 = [[0 for _ in range(w-l2)] for _ in range(h)]
    for i in range(h):
      for j in range(w):
        if j < l2:
          rect1[i][j] = rect[i][j]
        else:
          rect2[i][j-l2] = rect[i][j]
    if level == 1:
      max_result = max(divide_rect(rect1, 2) * get_sum(rect2), max_result)
      max_result = max(divide_rect(rect2, 2) * get_sum(rect1), max_result)
    else:
      max_result = max(get_sum(rect1) * get_sum(rect2), max_result)

  return max_result

n, m = map(int, sys.stdin.readline().split())
rect = [[0 for _ in range(m)] for _ in range(n)]
for i in range(n):
  line = sys.stdin.readline()
  for j in range(m):
    rect[i][j] = int(line[j])

print(divide_rect(rect, 1))