import sys

def is_not_broken(n, unable):
  for i in str(n):
    if i in list(map(str, unable)):
      return False
  return True

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
unable = []
if (m != 0):
  unable = list(map(int, sys.stdin.readline().split()))

# +- 조절 (100번에서 +-)
move = abs(n-100)

# 번호 누르고 +- 조절
for i in range(1000001):
  if (m == 0):
    move = min(move, len(str(n)))
    break
  if is_not_broken(i, unable):
    move = min(move, abs(i-n) + len(str(i)))

print(move)