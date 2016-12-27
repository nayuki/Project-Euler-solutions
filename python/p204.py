# 
# Solution to Project Euler problem 204
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 10**9
	primes = eulerlib.list_primes(100)
	
	def count(primeindex, product):
		if primeindex == len(primes):
			return 1 if product <= LIMIT else 0
		else:
			result = 0
			while product <= LIMIT:
				result += count(primeindex + 1, product)
				product *= primes[primeindex]
			return result
	
	return str(count(0, 1))


if __name__ == "__main__":
	print(compute())
