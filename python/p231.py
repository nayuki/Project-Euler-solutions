# 
# Solution to Project Euler problem 231
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	N = 20000000
	K = 15000000
	smallestprimefactor = eulerlib.list_smallest_prime_factors(N)
	
	def factorial_prime_factor_sum(n):
		result = 0
		for i in range(n + 1):
			j = i
			while j > 1:
				p = smallestprimefactor[j]
				result += p
				j //= p
		return result
	
	ans = factorial_prime_factor_sum(N) - factorial_prime_factor_sum(K) - factorial_prime_factor_sum(N - K)
	return str(ans)


if __name__ == "__main__":
	print(compute())
