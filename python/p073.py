# 
# Solution to Project Euler problem 73
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# The Stern-Brocot tree is an infinite binary search tree of all positive rational numbers,
# where each number appears only once and is in lowest terms.
# It is formed by starting with the two sentinels 0/1 and 1/1. Iterating infinitely in any order,
# between any two currently adjacent fractions Ln/Ld and Rn/Rd, insert a new fraction (Ln+Rn)/(Ld+Rd).
# See MathWorld for a visualization: http://mathworld.wolfram.com/Stern-BrocotTree.html
# 
# The natural algorithm is as follows:
#   # Counts the number of reduced fractions n/d such that leftN/leftD < n/d < rightN/rightD and d <= 12000.
#   # leftN/leftD and rightN/rightD must be adjacent in the Stern-Brocot tree at some point in the generation process.
#   def stern_brocot_count(leftn, leftd, rightn, rightd):
#     d = leftd + rightd
#     if d > 12000:
#       return 0
#     else:
#       n = leftn + rightn
#       return 1 + stern_brocot_count(leftn, leftd, n, d) + stern_brocot_count(n, d, rightn, rightd)
# But instead we use depth-first search on an explicit stack, because having
# a large number of stack frames seems to be supported on Linux but not on Windows.
def compute():
	ans = 0
	stack = [(1, 3, 1, 2)]
	while len(stack) > 0:
		leftn, leftd, rightn, rightd = stack.pop()
		d = leftd + rightd
		if d <= 12000:
			n = leftn + rightn
			ans += 1
			stack.append((n, d, rightn, rightd))
			stack.append((leftn, leftd, n, d))
	return str(ans)


if __name__ == "__main__":
	print(compute())
