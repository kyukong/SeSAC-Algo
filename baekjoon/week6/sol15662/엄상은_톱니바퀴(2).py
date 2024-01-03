import sys
import copy

def rotate(direction, wheel):
  if direction == 1:
    return wheel[7] + wheel[:7]
  else:
    return wheel[1:] + wheel[0]

def move(wheels, index, r_direction):
  new_wheels = copy.deepcopy(wheels)
  new_wheels[index] = rotate(r_direction, wheels[index])

  for i in range(index, len(wheels)-1):
    if wheels[i][2] != wheels[i+1][6]:
      # i번째 wheel 회전하기..
      if abs(index - (i+1)) % 2 == 0:        
        new_wheels[i+1] = rotate(r_direction, wheels[i+1])
      else:
        new_wheels[i+1] = rotate(-r_direction, wheels[i+1])
    else:
      break
  for i in range(index, 0, -1):
    if wheels[i][6] != wheels[i-1][2]:
      if abs(index - (i-1)) % 2 == 0:        
        new_wheels[i-1] = rotate(r_direction, wheels[i-1])
      else:
        new_wheels[i-1] = rotate(-r_direction, wheels[i-1])
    else:
      break
  return new_wheels

T = int(sys.stdin.readline())
wheels = []
for _ in range(T):
  wheels.append(sys.stdin.readline().split()[0])
K = int(sys.stdin.readline())
for i in range(K):
  i, d = map(int, sys.stdin.readline().split())
  wheels = move(wheels, i - 1, d)

count = 0
for w in wheels:
  if w[0] == '1':
    count += 1
print(count)