# 
# Solution to Project Euler problem 115
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# How many ways can a row n units long be filled, where red blocks are
# at least m units long? Denote this quantity as ways[n].
# Compute n = 0 manually as a base case.
# 
# Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
# - If the item is a black square, then the rest of the row is allowed
#   to be anything of length n-1. Add ways[n-1].
# - If the item is a red block with length k where k >= m, then:
#   - If k = n, then the whole row is filled by this red block. Add 1.
#   - Otherwise k < n, this red block is followed by a black square, then followed
#     by anything of length n-k-1. So add ways[n-m-1] + ways[n-m-2] + ... + ways[0].
def compute():
	# Dynamic programming
	M = 50
	ways = [1]
	for n in itertools.count(1):
		s = ways[n - 1] + sum(ways[ : max(n - M, 0)])
		if n >= M:
			s += 1
		ways.append(s)
		if s > 1000000:
			return str(n)


if __name__ == "__main__":
	print(compute())
