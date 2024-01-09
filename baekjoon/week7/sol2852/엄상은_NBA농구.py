import sys

n = int(input())
goal_1 = 0
goal_2 = 0
time_1 = 0 # 초단위로 계산
time_2 = 0
prev_goal_time = 0 # 초 단위로 직전 골 타임 계산

for _ in range(n):
  team, time = sys.stdin.readline().split()
  time = int(time.split(':')[0]) * 60 + int(time.split(':')[1])
  if team == '1':
    goal_1 += 1
    if goal_1 <= goal_2:
      time_2 += time - prev_goal_time
    elif goal_1 > goal_2 + 1:
      time_1 += time - prev_goal_time
  else:
    goal_2 += 1
    if goal_2 <= goal_1:
      time_1 += time - prev_goal_time
    elif goal_2 > goal_1 + 1:
      time_2 += time - prev_goal_time
  prev_goal_time = time

if goal_1 > goal_2:
  time_1 += 48 * 60 - prev_goal_time
elif goal_1 < goal_2:
  time_2 += 48 * 60 - prev_goal_time

print(str(time_1//60).zfill(2) + ":" + str(time_1%60).zfill(2))
print(str(time_2//60).zfill(2) + ":" + str(time_2%60).zfill(2))