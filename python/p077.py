# 
# Solution to Project Euler problem 77
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	for n in itertools.count(2):
		if num_prime_sum_ways(n) > 5000:
			return str(n)


primes = [2]

def num_prime_sum_ways(n):
	for i in range(primes[-1] + 1, n + 1):
		if eulerlib.is_prime(i):
			primes.append(i)
	
	ways = [0] * (n + 1)
	ways[0] = 1
	for p in primes:
		for i in range(n + 1 - p):
			ways[i + p] += ways[i]
	return ways[n]


if __name__ == "__main__":
	print(compute())
