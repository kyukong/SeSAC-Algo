# 입력: 전위순회한 결과 (루트-왼쪽-오른쪽)
# 출력: 후위순회한 결과 (왼쪽-오른쪽-루트)
import sys
sys.setrecursionlimit(20_000) # 재귀 제한 풀기

def post(start, end):
  if start > end:
    return
  root = pre[start]
  mid = end + 1
  for i in range(start+1, end+1):
    if (root < pre[i]):
      mid = i
      break

  post(start+1, mid-1) # 왼
  post(mid, end) # 오
  print(root) # 루트

pre = []
while 1: # 입력 개수 모를 때 처리 방법
  try:
    pre.append(int(input()))
  except:
    break
  
post(0, len(pre)-1)
