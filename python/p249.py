# 
# Solution to Project Euler problem 249
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 5000
	MODULUS = 10**16
	
	# Use dynamic programming. count[i] is the number of subsets of primes with the sum of i, modulo MODULUS.
	count = [0] * (LIMIT**2 // 2)
	count[0] = 1
	s = 0  # Sum of all primes seen so far, and thus the highest index among nonzero entries in 'count'
	for p in eulerlib.list_primes(LIMIT):
		for i in reversed(range(s + 1)):
			count[i + p] = (count[i + p] + count[i]) % MODULUS
		s += p
	
	isprime = eulerlib.list_primality(s + 1)
	ans = sum(count[i] for i in range(s + 1) if isprime[i]) % MODULUS
	return str(ans)


if __name__ == "__main__":
	print(compute())
