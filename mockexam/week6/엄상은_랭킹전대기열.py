import sys

p, m = map(int, sys.stdin.readline().split())
rooms = []
for i in range(p):
  player = list(sys.stdin.readline().split())
  player[0] = int(player[0])
  if not rooms:
    rooms.append([[player[1], player[0]]]) # 닉네임, 레벨
  else:
    is_found = False
    for r_index in range(len(rooms)):
      if abs(rooms[r_index][0][1] - player[0]) <= 10 and len(rooms[r_index]) < m:
        rooms[r_index].append([player[1], player[0]])
        is_found = True
        break
    if not is_found:
      rooms.append([[player[1], player[0]]])

# print
for room in rooms:
  if len(room) == m:
    print('Started!')
  else:
    print('Waiting!')  
  for player in sorted(room):
    print(player[1], player[0])
