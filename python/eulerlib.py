# 
# Shared code for solutions to Project Euler problems
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import array, math
from typing import Any, Callable, Dict, Generator, Generic, List, Optional, Protocol, TypeVar, cast


# Returns the number of 1's in the binary representation of
# the non-negative integer x. Also known as Hamming weight.
def popcount(x: int) -> int:
	return bin(x).count("1")


# Given integer x, this returns the integer floor(sqrt(x)).
def sqrt(x: int) -> int:
	assert x >= 0
	i: int = 1
	while i * i <= x:
		i *= 2
	y: int = 0
	while i > 0:
		if (y + i)**2 <= x:
			y += i
		i //= 2
	return y


# Tests whether x is a perfect square, for any integer x.
def is_square(x: int) -> bool:
	if x < 0:
		return False
	y: int = sqrt(x)
	return y * y == x


# Tests whether the given integer is a prime number.
def is_prime(x: int) -> bool:
	if x <= 1:
		return False
	elif x <= 3:
		return True
	elif x % 2 == 0:
		return False
	else:
		for i in range(3, sqrt(x) + 1, 2):
			if x % i == 0:
				return False
		return True


# Returns a list of True and False indicating whether each number is prime.
# For 0 <= i <= n, result[i] is True if i is a prime number, False otherwise.
def list_primality(n: int) -> List[bool]:
	# Sieve of Eratosthenes
	result: List[bool] = [True] * (n + 1)
	result[0] = result[1] = False
	for i in range(sqrt(n) + 1):
		if result[i]:
			for j in range(i * i, len(result), i):
				result[j] = False
	return result


# Returns all the prime numbers less than or equal to n, in ascending order.
# For example: listPrimes(97) = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, ..., 83, 89, 97].
def list_primes(n: int) -> List[int]:
	return [i for (i, isprime) in enumerate(list_primality(n)) if isprime]


# Yields prime numbers in ascending order from 2 to limit (inclusive).
def prime_generator(limit: int) -> Generator[int,None,None]:
	if limit >= 2:
		yield 2
	
	# Sieve of Eratosthenes, storing only odd numbers starting at 3
	isprime: array.array[int] = array.array("B", b"\x01" * ((limit - 1) // 2))
	sieveend: int = sqrt(limit)
	for i in range(len(isprime)):
		if isprime[i] == 1:
			p: int = i * 2 + 3
			yield p
			if i <= sieveend:
				for j in range((p * p - 3) >> 1, len(isprime), p):
					isprime[j] = 0


def list_smallest_prime_factors(n: int) -> List[int]:
	result: List[Optional[int]] = [None] * (n + 1)
	limit = sqrt(n)
	for i in range(2, len(result)):
		if result[i] is None:
			result[i] = i
			if i <= limit:
				for j in range(i * i, n + 1, i):
					if result[j] is None:
						result[j] = i
	return cast(List[int], result)


def list_totients(n: int) -> List[int]:
	result: List[int] = list(range(n + 1))
	for i in range(2, len(result)):
		if result[i] == i:  # i is prime
			for j in range(i, len(result), i):
				result[j] -= result[j] // i
	return result


def binomial(n: int, k: int) -> int:
	assert 0 <= k <= n
	return math.factorial(n) // (math.factorial(k) * math.factorial(n - k))


E = TypeVar("E", bound="_Comparable")

class _Comparable(Protocol):
	def __lt__(self: E, other: E) -> bool: ...
	def __le__(self: E, other: E) -> bool: ...
	def __gt__(self: E, other: E) -> bool: ...
	def __ge__(self: E, other: E) -> bool: ...


def next_permutation(arr: List[E]) -> bool:
	# Find non-increasing suffix
	i: int = len(arr) - 1
	while i > 0 and arr[i - 1] >= arr[i]:
		i -= 1
	if i <= 0:
		return False
	
	# Find successor to pivot
	j: int = len(arr) - 1
	while arr[j] <= arr[i - 1]:
		j -= 1
	arr[i - 1], arr[j] = arr[j], arr[i - 1]
	
	# Reverse suffix
	arr[i : ] = arr[len(arr) - 1 : i - 1 : -1]
	return True
