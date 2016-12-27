# 
# Solution to Project Euler problem 87
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 50000000
	primes = eulerlib.list_primes(eulerlib.sqrt(LIMIT))
	
	sums = {0}
	for i in range(2, 5):
		newsums = set()
		for p in primes:
			q = p**i
			if q > LIMIT:
				break
			for x in sums:
				if x + q <= LIMIT:
					newsums.add(x + q)
		sums = newsums
	return str(len(sums))


if __name__ == "__main__":
	print(compute())
