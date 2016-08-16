# 
# Shared code for solutions to Project Euler problems
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math, sys
if sys.version_info.major == 2:
	range = xrange


# Given integer x, this returns the integer floor(sqrt(x)).
def sqrt(x):
	assert x >= 0
	i = 1
	while i * i <= x:
		i *= 2
	y = 0
	while i > 0:
		if (y + i)**2 <= x:
			y += i
		i //= 2
	return y


# Tests whether the given integer is a prime number.
def is_prime(x):
	if x <= 1:
		return False
	elif x <= 3:
		return True
	elif x % 2 == 0:
		return False
	else:
		for i in range(6, sqrt(x) + 1, 6):
			for j in (i - 1, i + 1):
				if x % i == 0:
					return False
		return True


# Returns a list of True and False indicating whether each number is prime.
# For 0 <= i <= n, result[i] is True if i is a prime number, False otherwise.
def list_primality(n):
	# Sieve of Eratosthenes
	result = [True] * (n + 1)
	result[0] = result[1] = False
	for i in range(sqrt(n) + 1):
		if result[i]:
			for j in range(i * i, len(result), i):
				result[j] = False
	return result


# Returns all the prime numbers less than or equal to n, in ascending order.
# For example: listPrimes(97) = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, ..., 83, 89, 97].
def list_primes(n):
	return [i for (i, isprime) in enumerate(list_primality(n)) if isprime]


# Yields prime numbers in ascending order from 2 to limit (inclusive).
def prime_generator(limit):
	if limit >= 2:
		yield 2
	
	# Sieve of Eratosthenes, storing only odd numbers starting at 3
	isprime = [True] * ((limit - 1) // 2)
	sieveend = sqrt(limit)
	for i in range(len(isprime)):
		if isprime[i]:
			p = i * 2 + 3
			yield p
			if i <= sieveend:
				for j in range((p * p - 3) >> 1, len(isprime), p):
					isprime[j] = False


def binomial(n, k):
	assert n >= 0 and 0 <= k <= n
	return math.factorial(n) // (math.factorial(k) * math.factorial(n - k))
