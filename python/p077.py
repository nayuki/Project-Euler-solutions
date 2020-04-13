# 
# Solution to Project Euler problem 77
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	cond = lambda n: num_prime_sum_ways(n) > 5000
	ans = next(filter(cond, itertools.count(2)))
	return str(ans)


primes = [2]

def num_prime_sum_ways(n):
	for i in range(primes[-1] + 1, n + 1):
		if eulerlib.is_prime(i):
			primes.append(i)
	
	ways = [1] + [0] * n
	for p in primes:
		for i in range(n + 1 - p):
			ways[i + p] += ways[i]
	return ways[n]


if __name__ == "__main__":
	print(compute())
