import sys
text = sys.stdin.readline().split()[0]
bomb = sys.stdin.readline().split()[0]
bomb_l = [t for t in bomb]

text_l = []
for t in text:
  text_l.append(t)
  if text_l[len(text_l)-len(bomb_l):] == bomb_l:
    for _ in range(len(bomb_l)):
      text_l.pop()
if text_l:
  print(''.join(text_l))
else:
  print("FRULA")