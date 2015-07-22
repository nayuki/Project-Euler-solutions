# 
# Solution to Project Euler problem 27
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


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


# Given integer x, this returns the integer floor(sqrt(x)).
def sqrt(x):
	i = 1
	while i * i <= x:
		i *= 2
	y = 0
	while i > 0:
		if (y + i)**2 <= x:
			y += i
		i //= 2
	return y


def is_prime(x):
	if x < 2:
		return False
	for i in range(2, sqrt(x) + 1):
		if x % i == 0:
			return False
	return True


def count_consecutive_primes(a, b):
	for i in itertools.count():
		if not is_prime(i * i + i * a + b):
			return i


if __name__ == "__main__":
	print(compute())
