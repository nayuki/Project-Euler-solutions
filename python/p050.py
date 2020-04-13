# 
# Solution to Project Euler problem 50
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	ans = 0
	isprime = eulerlib.list_primality(999999)
	primes = eulerlib.list_primes(999999)
	consecutive = 0
	for i in range(len(primes)):
		sum = primes[i]
		consec = 1
		for j in range(i + 1, len(primes)):
			sum += primes[j]
			consec += 1
			if sum >= len(isprime):
				break
			if isprime[sum] and consec > consecutive:
				ans = sum
				consecutive = consec
	return str(ans)


if __name__ == "__main__":
	print(compute())
