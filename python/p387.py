# 
# Solution to Project Euler problem 387
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 10**14
	
	# Use a list container as a hack, because Python 2 does not support 'nonlocal' variables
	ans = [0]
	
	# Note: n must be a right-truncatable Harshad number, and the other arguments are properties of the number n.
	def find_harshad_primes(n, digitsum, isstrong):
		# Shift left by 1 digit, and try all 10 possibilities for the rightmost digit
		m = n * 10
		s = digitsum
		for i in range(10):
			if m >= LIMIT:
				break
			if isstrong and eulerlib.is_prime(m):
				ans[0] += m
			if m % s == 0:
				find_harshad_primes(m, s, eulerlib.is_prime(m // s))
			m += 1
			s += 1
	
	for i in range(1, 10):  # All one-digit numbers are trivially Harshad numbers
		find_harshad_primes(i, i, False)
	return str(ans[0])


if __name__ == "__main__":
	print(compute())
