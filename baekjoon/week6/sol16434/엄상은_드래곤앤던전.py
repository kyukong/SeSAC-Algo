import sys

N, atk = map(int, sys.stdin.readline().split())
info = [[0, 0, 0] for _ in range(N)]
for i in range(N):
  # t가 1이면 몬스터 공격력 a, 생명력 h
  # t가 2이면 공격력 a 증가, 생명력 h 회복
  info[i] = list(map(int, sys.stdin.readline().split()))

# 처음 max값은 cur과 같음
cur_hp = 1

# 마이너스로 떨어질 때 그 양만큼 저장
min_v = 0
need = 0

for i in info:
  if i[0] == 1:
    t_count = int(i[2] / atk + 1) # 몬스터와 싸움 횟수
    if i[2] % atk == 0:
      t_count -= 1
    need += i[1] * (t_count - 1)
    min_v = max(need + 1, min_v)
  else:
    atk += i[1]
    need -= i[2]
    if need < 0:
      need = 0

print(min_v)