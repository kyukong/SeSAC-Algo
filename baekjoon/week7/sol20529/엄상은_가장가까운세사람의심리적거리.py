import sys, itertools

def get_distance(a, b):
  count = 0
  for i in range(4):
    if a[i] != b[i]:
      count += 1
  return count

T = int(sys.stdin.readline())
for _ in range(T):
  N = int(sys.stdin.readline())
  students = sys.stdin.readline().split()
  counts = dict()
  min_sum = 13
  for i in students:
    counts[i] = counts.get(i, 0) + 1
    if counts[i] == 3:
      min_sum = 0
  if min_sum == 0:
    print(min_sum)
    continue
  for combi in itertools.combinations(students, 3):
    dist_sum = get_distance(combi[0], combi[1]) + get_distance(combi[0], combi[2]) + get_distance(combi[2], combi[1])
    min_sum = min(dist_sum, min_sum)
  print(min_sum)