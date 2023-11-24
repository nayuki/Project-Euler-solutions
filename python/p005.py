# 
# Solution to Project Euler problem 5
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math


def compute():
	ans = math.lcm(*range(1, 21))
	return str(ans)


if __name__ == "__main__":
	print(compute())
