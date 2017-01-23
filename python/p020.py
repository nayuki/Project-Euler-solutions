# 
# Solution to Project Euler problem 20
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math


# We do a straightforward computation thanks to Python's built-in arbitrary precision integer type.
def compute():
	n = math.factorial(100)
	ans = sum(int(c) for c in str(n))
	return str(ans)


if __name__ == "__main__":
	print(compute())
