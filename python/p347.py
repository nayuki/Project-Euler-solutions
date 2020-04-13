# 
# Solution to Project Euler problem 347
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 10000000
	
	possible = set()
	primes = eulerlib.list_primes(LIMIT // 2)
	end = eulerlib.sqrt(LIMIT)
	for i in range(len(primes)):
		p = primes[i]
		if p > end:
			break
		for j in range(i + 1, len(primes)):
			q = primes[j]
			lcm = p * q
			if lcm > LIMIT:
				break
			multlimit = LIMIT // lcm
			
			multiplier = 1
			while multiplier * p <= multlimit:
				multiplier *= p
			maxmult = multiplier
			while multiplier % p == 0:
				multiplier //= p
				while multiplier * q <= multlimit:
					multiplier *= q
				maxmult = max(multiplier, maxmult)
			possible.add(maxmult * lcm)
	
	ans = sum(possible)
	return str(ans)


if __name__ == "__main__":
	print(compute())
