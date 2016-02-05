# 
# Solution to Project Euler problem 134
# by Project Nayuki
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
		m = (q - p) * reciprocal_mod(k % q, q) % q
		ans += m * k + p
	return str(ans)


# Returns x^-1 mod m. Note that x * x^-1 mod m = x^-1 * x mod m = 1.
def reciprocal_mod(x, m):
	assert m > 0 and 0 <= x < m
	
	# Based on a simplification of the extended Euclidean algorithm
	y = x
	x = m
	a = 0
	b = 1
	while y != 0:
		a, b = b, a - x // y * b
		x, y = y, x % y
	if x == 1:
		return a % m
	else:
		raise ValueError("Reciprocal does not exist")


if __name__ == "__main__":
	print(compute())
