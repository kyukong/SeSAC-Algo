import sys
import math

def is_prime(number):
  if number == 2:
    return True
  for i in range(2, int(math.sqrt(number)) + 1):
    if number % i == 0:
      return False
  return True

n = int(sys.stdin.readline())
prime = []

for i in range(2, n+1):
  if is_prime(i):
    prime.append(i)

e = 0
count = 0
sum = 0
for s in range(0, len(prime)):
  while sum < n and e < len(prime):
    sum += prime[e]
    e += 1
  if sum == n:
    count += 1
  sum -= prime[s]

print(count)