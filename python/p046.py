# 
# Solution to Project Euler problem 46
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	ans = next(itertools.filterfalse(test_goldbach, itertools.count(9, 2)))
	return str(ans)


def test_goldbach(n):
	if n % 2 == 0 or eulerlib.is_prime(n):
		return True
	for i in itertools.count(1):
		k = n - 2 * i * i
		if k <= 0:
			return False
		elif eulerlib.is_prime(k):
			return True


if __name__ == "__main__":
	print(compute())
