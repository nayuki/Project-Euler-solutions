# 
# Solution to Project Euler problem 146
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Right off the bat, we can exclude 90% of the candidates by the following observations:
# - If n = 1 mod 2, then n^2 + 1 = 0 mod 2 which is composite.
# - Thus we require n = 0 mod 2.
# - If n = 1 mod 5, then n^2 + 9 = 0 mod 5 which is composite.
# - If n = 2 mod 5, then n^2 + 1 = 0 mod 5 which is composite.
# - If n = 3 mod 5, then n^2 + 1 = 0 mod 5 which is composite.
# - If n = 4 mod 5, then n^2 + 9 = 0 mod 5 which is composite.
# - Thus we require n = 0 mod 5.
# - Taking these two together and using the Chinese remainder theorem (CRT), we require n = 0 mod 10.
# 
# For each value of n, after we generate the set {n^2 + 1, n^2 + 3, ..., n^2 + 27}, it's more efficient to take each
# prime number and test whether it divides any number, rather than take each number and test it against all prime numbers.
# This is because some numbers in this set are prime so the latter method tests some numbers against all the primes;
# the former method will bail out early as soon as ~any~ number in the set has a small prime factor.
# 
# The rest of the algorithm is implemented straightforwardly.
def compute():
	LIMIT = 150000000
	
	INCREMENTS = [1, 3, 7, 9, 13, 27]  # Must be in non-decreasing order
	NON_INCREMENTS = set(range(INCREMENTS[-1])) - set(INCREMENTS)
	
	maxnumber = LIMIT**2 + INCREMENTS[-1]
	primes = eulerlib.list_primes(eulerlib.sqrt(maxnumber))
	
	def has_consecutive_primes(n):
		# Generate the set of numbers to test for primality
		n2 = n**2
		temp = [(n2 + k) for k in INCREMENTS]
		
		# Test that each number is prime.
		# Note: The nesting of the loops can be reversed, but this way is much faster.
		if any((x != p and x % p == 0)
				for p in primes
				for x in temp):
			return False
		
		# Test that each number that is not an increment is composite.
		# This checks that the prime numbers we found are in fact consecutive.
		return all((not is_prime(n2 + k)) for k in NON_INCREMENTS)
	
	
	def is_prime(n):
		end = eulerlib.sqrt(n)
		for p in primes:
			if p > end:
				break
			if n % p == 0:
				return False
		return True
	
	
	ans = sum(n for n in range(0, LIMIT, 10) if has_consecutive_primes(n))
	return str(ans)


if __name__ == "__main__":
	print(compute())
