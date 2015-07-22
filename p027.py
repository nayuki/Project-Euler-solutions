# 
# Solution to Project Euler problem 27
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	ans = 0
	maxconsec = 0
	for a in range(-999, 1000):
		for b in range(-999, 1000):
			consec = count_consecutive_primes(a, b)
			if consec > maxconsec:
				ans = a * b
				maxconsec = consec
	return str(ans)


def count_consecutive_primes(a, b):
	for i in itertools.count():
		if not eulerlib.is_prime(i * i + i * a + b):
			return i


if __name__ == "__main__":
	print(compute())
