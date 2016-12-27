# 
# Solution to Project Euler problem 304
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	BASE = 10**14
	SEARCH_RANGE = 10000000  # Number of candidates starting from BASE to search for primes. Hopefully there are 100 000 primes among here.
	MODULUS = 1234567891011
	
	
	# iscomposite[i] pertains to the number BASE + i
	# Sieve of Eratosthenes, but starting at BASE
	iscomposite = [False] * SEARCH_RANGE
	primes = eulerlib.list_primes(eulerlib.sqrt(BASE + SEARCH_RANGE))
	for p in primes:
		for i in range((BASE + p - 1) // p * p - BASE, len(iscomposite), p):
			iscomposite[i] = True
	
	# Returns p - BASE, where p is the next prime after n + BASE
	def next_prime(n):
		while True:
			n += 1
			if n >= len(iscomposite):
				raise AssertionError("Search range exhausted")
			if not iscomposite[n]:
				return n
	
	
	ans = 0
	p = 0
	for i in range(100000):
		p = next_prime(p)
		ans = (ans + fibonacci_mod(BASE + p, MODULUS)) % MODULUS
	return str(ans)


def fibonacci_mod(n, mod):
	a, b = 0, 1
	binary = bin(n)[2 : ]
	for bit in binary:
		a, b = a * (b * 2 - a), a * a + b * b
		if bit == "1":
			a, b = b, a + b
		a %= mod
		b %= mod
	return a


if __name__ == "__main__":
	print(compute())
