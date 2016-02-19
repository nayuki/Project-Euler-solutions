# 
# Solution to Project Euler problem 73
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import sys


# The Stern-Brocot tree is an infinite binary search tree of all positive rational numbers,
# where each number appears only once and is in lowest terms.
# It is formed by starting with the two sentinels 0/1 and 1/1. Iterating infinitely in any order,
# between any two currently adjacent fractions Ln/Ld and Rn/Rd, insert a new fraction (Ln+Rn)/(Ld+Rd).
# See MathWorld for a visualization: http://mathworld.wolfram.com/Stern-BrocotTree.html
# This algorithm uses a lot of stack space (about 12000 frames). It seems to work on Linux but crashes on Windows.
def compute():
	sys.setrecursionlimit(12000 + 100)
	return str(stern_brocot_count(1, 3, 1, 2))


# Counts the number of reduced fractions n/d such that leftN/leftD < n/d < rightN/rightD and d <= 12000.
# leftN/leftD and rightN/rightD must be adjacent in the Stern-Brocot tree at some point in the generation process.
def stern_brocot_count(leftn, leftd, rightn, rightd):
	d = leftd + rightd
	if d > 12000:
		return 0
	else:
		n = leftn + rightn
		return 1 + stern_brocot_count(leftn, leftd, n, d) + stern_brocot_count(n, d, rightn, rightd)


if __name__ == "__main__":
	print(compute())
