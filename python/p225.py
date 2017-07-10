# 
# Solution to Project Euler problem 225
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	INDEX = 124  # 1-based
	stream = (i for i in itertools.count(1, 2) if not has_tribonacci_multiple(i))
	ans = next(itertools.islice(stream, INDEX - 1, None))
	return str(ans)


def has_tribonacci_multiple(i):
	seen = set()
	a, b, c = 1, 1, 1
	while True:
		key = (a, b, c)
		if key in seen:
			return False
		seen.add(key)
		if a % i == 0:
			return True
		a, b, c = b, c, (a + b + c) % i


if __name__ == "__main__":
	print(compute())
