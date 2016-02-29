# 
# Solution to Project Euler problem 225
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools, sys
if sys.version_info.major == 2:
	filterfalse = itertools.ifilterfalse
else:
	filterfalse = itertools.filterfalse


def compute():
	ans = next(itertools.islice(filterfalse(has_tribonacci_multiple, itertools.count(1, 2)), 123, None))
	return str(ans)


def has_tribonacci_multiple(i):
	seen = set()
	a, b, c = 1, 1, 1
	while True:
		if a % i == 0:
			return True
		key = (a, b, c)
		if key in seen:
			return False
		seen.add(key)
		a, b, c = b, c, (a + b + c) % i


if __name__ == "__main__":
	print(compute())
