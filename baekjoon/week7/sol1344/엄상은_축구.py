import itertools

# 18cN p^N * q^(1-N)
def get_probability(number, p):
  return len(list(itertools.combinations(range(1, 19), number))) * pow(p, number) * pow(1-p, 18-number)

prime = [2, 3, 5, 7, 11, 13, 17]

a_goal = int(input()) / 100
b_goal = int(input()) / 100

# 둘 중 하나라도 소수로 득점할 확률
# = (전체 확률) - (둘 다 소수가 아니게 득점할 확률)

a_p = 0
b_p = 0
for i in range(0, 19):
  if i not in prime:
    a_p += get_probability(i, a_goal)
    b_p += get_probability(i, b_goal)
print(1 - a_p * b_p)
