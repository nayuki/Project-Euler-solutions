# 
# Solution to Project Euler problem 134
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# Let p and q be the two primes. Let k be the smallest power of 10 that exceeds p.
# The number that we seek is n = mk + p, where n is divisible by q, and m is minimized.
# (For example, p = 19, q = 23, k = 100, m = 12, n = 1219.)
# 
# Firstly, n = mk + p = 0 mod q. By rearrangement, m = -p k^-1 mod q. (k^-1 exists because q is coprime with 10.)
# Then of course the smallest m that satisfies the divisibility condition is the one such that 0 <= m < q.
def compute():
	ans = 0
	primes = eulerlib.list_primes(2000000)
	for i in itertools.count(2):
		p = primes[i]
		q = primes[i + 1]
		if p > 1000000:
			break
		k = 1
		while k < p:
			k *= 10
		m = (q - p) * pow(k, -1, q) % q
		ans += m * k + p
	return str(ans)


if __name__ == "__main__":
	print(compute())
