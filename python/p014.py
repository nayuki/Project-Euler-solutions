# 
# Solution to Project Euler problem 14
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, functools, sys


# We compute the Collatz chain length for every integer in the range according to the iteration rule.
# Also, we cache the Collatz value for all integer arguments to speed up the computation.
def compute():
	sys.setrecursionlimit(3000)
	ans = max(range(1, 1000000), key=collatz_chain_length)
	return str(ans)


@functools.cache
def collatz_chain_length(x):
	if x == 1:
		return 1
	if x % 2 == 0:
		y = x // 2
	else:
		y = x * 3 + 1
	return collatz_chain_length(y) + 1


if __name__ == "__main__":
	print(compute())
