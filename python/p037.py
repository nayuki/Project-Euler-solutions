# 
# Solution to Project Euler problem 37
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	ans = sum(itertools.islice(filter(is_truncatable_prime, itertools.count(10)), 11))
	return str(ans)


def is_truncatable_prime(n):
	# Test if left-truncatable
	i = 10
	while i <= n:
		if not eulerlib.is_prime(n % i):
			return False
		i *= 10
	
	# Test if right-truncatable
	while n > 0:
		if not eulerlib.is_prime(n):
			return False
		n //= 10
	return True


if __name__ == "__main__":
	print(compute())
