import sys

g = int(sys.stdin.readline())

s = 1
e = 1
val = 0
is_found = False
while e <= 50000:
  if val < g:
    val -= e * e
    e += 1
    val += e * e
  elif val > g:
    val += s * s
    s += 1
    val -= s * s
  else:
    print(e)
    is_found = True
    val -= e * e
    e += 1
    val += e * e

if not is_found:
  print(-1)
