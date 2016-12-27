# 
# Solution to Project Euler problem 49
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 10000
	isprime = eulerlib.list_primality(LIMIT - 1)
	for base in range(1000, LIMIT):
		if isprime[base]:
			for step in range(1, LIMIT):
				a = base + step
				b = a + step
				if     a < LIMIT and isprime[a] and has_same_digits(a, base) \
				   and b < LIMIT and isprime[b] and has_same_digits(b, base) \
				   and (base != 1487 or a != 4817):
					return str(base) + str(a) + str(b)
	raise RuntimeError("Not found")


def has_same_digits(x, y):
	return sorted(str(x)) == sorted(str(y))


if __name__ == "__main__":
	print(compute())
