# 
# Solution to Project Euler problem 15
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# This is a classic combinatorics problem. To get from the top left corner to the bottom right corner of an N*N grid,
# it involves making exactly N moves right and N moves down in some order. Because each individual down or right move
# is indistinguishable, there are exactly 2N choose N (binomial coefficient) ways of arranging these moves.
def compute():
	return str(eulerlib.binomial(40, 20))


if __name__ == "__main__":
	print(compute())
