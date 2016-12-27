# 
# Solution to Project Euler problem 72
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	totients = eulerlib.list_totients(10**6)
	ans = sum(itertools.islice(totients, 2, None))
	return str(ans)


if __name__ == "__main__":
	print(compute())
