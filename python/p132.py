# 
# Solution to Project Euler problem 132
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	# Among the integers starting from 2, take the sum of
	# the first 40 integers satisfying the filter condition
	cond = lambda i: eulerlib.is_prime(i) and repunit_mod(10**9, i) == 0
	ans = sum(itertools.islice(filter(cond, itertools.count(2)), 40))
	return str(ans)


def repunit_mod(k, m):
	return (pow(10, k, m * 9) - 1) // 9


if __name__ == "__main__":
	print(compute())
