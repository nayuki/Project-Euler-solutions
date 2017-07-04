# 
# Solution to Project Euler problem 26
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	ans = max(range(1, 1000), key=reciprocal_cycle_len)
	return str(ans)


def reciprocal_cycle_len(n):
	seen = {}
	x = 1
	for i in itertools.count():
		if x in seen:
			return i - seen[x]
		else:
			seen[x] = i
			x = x * 10 % n


if __name__ == "__main__":
	print(compute())
