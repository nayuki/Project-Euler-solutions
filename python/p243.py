# 
# Solution to Project Euler problem 243
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions, itertools


def compute():
	TARGET = fractions.Fraction(15499, 94744)
	numer = 1
	denom = 1
	
	# This is related to computing totients.
	# To make the totient smaller relative to the number, we must add new prime factors.
	p = 2
	while True:
		numer *= p - 1
		denom *= p
		if fractions.Fraction(numer, denom) < TARGET:
			for i in range(1, p + 1):
				n = numer * i
				d = denom * i - 1
				if fractions.Fraction(n, d) < TARGET:
					return str(d + 1)
		p = next_prime(p)


def next_prime(n):
	for i in itertools.count(n + 1):
		if eulerlib.is_prime(i):
			return i


if __name__ == "__main__":
	print(compute())
