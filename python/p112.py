# 
# Solution to Project Euler problem 112
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	count = 0
	for i in itertools.count(1):
		s = str(i)
		t = "".join(sorted(s))
		if s != t and s[ : : -1] != t:
			count += 1  # i is bouncy
		if count * 100 == 99 * i:
			return str(i)


if __name__ == "__main__":
	print(compute())
