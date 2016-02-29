# 
# Solution to Project Euler problem 24
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools, sys
if sys.version_info.major == 2:
	range = xrange


def compute():
	arr = list(range(10))
	temp = itertools.islice(itertools.permutations(arr), 999999, None)
	return "".join([str(x) for x in next(temp)])


if __name__ == "__main__":
	print(compute())
