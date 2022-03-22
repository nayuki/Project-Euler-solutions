# 
# Solution to Project Euler problem 116
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LENGTH = 50
	return str(sum(count_ways(LENGTH, i) for i in range(2, 5)))


# How many ways can a row n units long be filled with grey squares 1 unit long
# and colored tiles m units long? Denote this quantity as ways[n].
# Compute n = 0 manually as a base case.
# 
# Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
# - If the item is a grey square, then the rest of the row
#   is allowed to be anything of length n-1. Add ways[n-1].
# - If the item is a colored tile of length m where m <= n, then the
#   rest of the row can be anything of length n-m. Add ways[n-m].
# 
# At the end, return ways[length]-1 to exclude the case where the row is all grey squares.
def count_ways(length, m):
	# Dynamic programming
	ways = [1] + [0] * length
	for n in range(1, len(ways)):
		ways[n] += ways[n - 1]
		if n >= m:
			ways[n] += ways[n - m]
	return ways[-1] - 1


if __name__ == "__main__":
	print(compute())
