# 
# Solution to Project Euler problem 117
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# How many ways can a row n units long be filled with:
# - grey squares 1 unit long
# - Red tiles 2 units long
# - Green tiles 3 units long
# - Blue tiles 4 units long
# Denote this quantity as ways[n].
# 
# Compute n = 0 manually as a base case.
# Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
# - grey square (n>=1): Rest of the row can be anything of length n-1. Add ways[n-1].
# - Red tile    (n>=2): Rest of the row can be anything of length n-2. Add ways[n-2].
# - Green tile  (n>=3): Rest of the row can be anything of length n-3. Add ways[n-3].
# - Blue tile   (n>=4): Rest of the row can be anything of length n-4. Add ways[n-4].
def compute():
	# Dynamic programming
	LENGTH = 50
	ways = [1] + [0] * LENGTH
	for n in range(1, len(ways)):
		ways[n] += sum(ways[max(n - 4, 0) : n])
	return str(ways[-1])


if __name__ == "__main__":
	print(compute())
