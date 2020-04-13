# 
# Solution to Project Euler problem 211
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import array, eulerlib


def compute():
	LIMIT = 64000000
	
	# Can be any number >= 1, but it's most beneficial to use a product of unique small primes excluding 2
	RESIDUE_TEST = 3 * 5 * 7 * 11 * 13
	
	isresidue = [False] * RESIDUE_TEST
	for i in range(RESIDUE_TEST):
		isresidue[i * i % RESIDUE_TEST] = True
	
	def is_perfect_square(x):
		# Optional optimization: Check if x is a quadratic residue modulo some number.
		# The modulus was chosen to be a product of k primes; in this case, k = 5.
		# If x is a square, then it must be a quadratic residue modulo each prime.
		# For each prime p, there is an approximately half chance that an arbitrary number
		# is a residue mod p. Thus with 5 primes, only about 1/32 of candidates remain.
		# Note that the prime 2 tells us nothing about whether x is a square, so we exclude it.
		return isresidue[x % RESIDUE_TEST] and eulerlib.is_square(x)
	
	
	# Requires at least 640 MB of memory
	sigma2 = list_sigma2(LIMIT - 1)
	ans = sum(i for i in range(1, LIMIT) if is_perfect_square(sigma2[i]))
	return str(ans)


def list_sigma2(n):
	# If i has a prime factor p <= sqrt, then quasiprimefactor[i] = p.
	# Otherwise i > sqrt must be prime, and quasiprimefactor[i] = 0 because i may overflow an int16.
	sqrt = eulerlib.sqrt(n)
	quasiprimefactor = array.array("H", (0 for _ in range(n + 1)))
	
	# Richer version of the sieve of Eratosthenes
	for i in range(2, sqrt + 1):
		if quasiprimefactor[i] == 0:
			quasiprimefactor[i] = i
			for j in range(i * i, n + 1, i):
				if quasiprimefactor[j] == 0:
					quasiprimefactor[j] = i
	
	sigma2 = array.array("Q", (0 for _ in range(n + 1)))
	sigma2[1] = 1
	for i in range(2, len(sigma2)):
		p = quasiprimefactor[i]
		if p == 0:
			p = i
		sum = 1
		j = i
		p2 = p * p
		k = p2
		while j % p == 0:
			sum += k
			j //= p
			k *= p2
		sigma2[i] = sum * sigma2[j]
	return sigma2


if __name__ == "__main__":
	print(compute())
