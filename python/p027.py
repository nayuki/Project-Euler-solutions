# 
# Solution to Project Euler problem 27
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	# Note: b must be prime because when n = 0, the formula evaluates to just b.
	ans = max(((a, b) for a in range(-999, 1000) for b in range(2, 1000) if is_prime(b)), key=count_consecutive_primes)
	return str(ans[0] * ans[1])


def count_consecutive_primes(ab):
	a, b = ab
	for i in itertools.count():
		n = i * i + i * a + b
		if not is_prime(n):
			return i


isprimecache = list(map(eulerlib.is_prime, range(1000)))

def is_prime(n):
	if n < 0:
		return False
	elif n < len(isprimecache):
		return isprimecache[n]
	else:
		return eulerlib.is_prime(n)


if __name__ == "__main__":
	print(compute())
