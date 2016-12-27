# 
# Solution to Project Euler problem 160
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = factorial_suffix(10**12)
	return str(ans)


# The last 5 digits of n!, excluding trailing zeros.
def factorial_suffix(n):
	twos = count_factors(n, 2) - count_factors(n, 5)  # Always non-negative for every n
	# We can reduce 'twos' because there is a cycle: 2^5 = 2^2505 = 32 mod 100000
	if twos >= 2505:
		twos = (twos - 5) % 2500 + 5
	return factorialish(n) * pow(2, twos, 100000) % 100000


# Equal to n! but with all factors of 2 and 5 removed and then modulo 10^5.
# The identity factorialIsh(n) = odd_factorialish(n) * even_factorialish(n) (mod 10^5) is true by definition.
def factorialish(n):
	return even_factorialish(n) * odd_factorialish(n) % 100000


# The product of {all even numbers from 1 to n}, but with all factors of 2 and 5 removed and then modulo 10^5.
# For example, even_factorialish(9) only considers the numbers {2, 4, 6, 8}. Divide each number by 2 to get {1, 2, 3, 4}. Thus even_factorialish(9) = factorialish(4).
def even_factorialish(n):
	if n == 0:
		return 1
	else:
		return factorialish(n // 2)


# The product of {all odd numbers from 1 to n}, but with all factors of 2 and 5 removed and then modulo 10^5.
# By definition, odd_factorialish() never considers any number that has a factor of 2. The product of the numbers that not a multiple of 5 are accumulated by factorial_coprime().
# Those that are a multiple of 5 are handled recursively by odd_factorialish(), noting that they are still odd after dividing by 5.
def odd_factorialish(n):
	if n == 0:
		return 1
	else:
		return odd_factorialish(n // 5) * factorial_coprime(n) % 100000


# The product of {all numbers from 1 to n that are coprime with 10}, modulo 10^5.
# The input argument can be taken modulo 10^5 because factorialoid(10^5) = 1, and each block of 10^5 numbers behaves the same.
def factorial_coprime(n):
	n %= 100000
	product = 1
	for i in range(1, n + 1):
		if i % 2 != 0 and i % 5 != 0:
			product = i * product % 100000
	return product


# Counts the number of factors of n in the set of integers {1, 2, ..., end}.
# For example, count_factors(25, 5) = 6 because {5, 10, 15, 20} each has one factor of 5, and 25 has two factors of 5.
def count_factors(end, n):
	if end == 0:
		return 0
	else:
		return end // n + count_factors(end // n, n)


if __name__ == "__main__":
	print(compute())
