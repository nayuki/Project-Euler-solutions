# 
# Solution to Project Euler problem 203
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, math


def compute():
	# Collect unique numbers in Pascal's triangle
	numbers = set(math.comb(n, k) for n in range(51) for k in range(n + 1))
	maximum = max(numbers)
	
	# Prepare list of squared primes
	primes = eulerlib.list_primes(math.isqrt(maximum))
	primessquared = [p * p for p in primes]
	
	def is_squarefree(n):
		for p2 in primessquared:
			if p2 > n:
				break
			if n % p2 == 0:
				return False
		return True
	
	# Sum up the squarefree numbers
	ans = sum(n for n in numbers if is_squarefree(n))
	return str(ans)


if __name__ == "__main__":
	print(compute())
