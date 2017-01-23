# 
# Solution to Project Euler problem 18
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# We create a new blank triangle with the same dimensions as the original big triangle.
# For each cell of the big triangle, we consider the sub-triangle whose top is at this cell,
# calculate the maximum path sum when starting from this cell, and store the result
# in the corresponding cell of the blank triangle.
# 
# If we start at a particular cell, what is the maximum path total? If the cell is at the
# bottom of the big triangle, then it is simply the cell's value. Otherwise the answer is
# the cell's value plus either {the maximum path total of the cell down and to the left}
# or {the maximum path total of the cell down and to the right}, whichever is greater.
# By computing the blank triangle's values from bottom up, the dependent values are always
# computed before they are utilized. This technique is known as dynamic programming.
def compute():
	for i in reversed(range(len(triangle) - 1)):
		for j in range(len(triangle[i])):
			triangle[i][j] += max(triangle[i + 1][j], triangle[i + 1][j + 1])
	return str(triangle[0][0])


triangle = [  # Mutable
	[75],
	[95,64],
	[17,47,82],
	[18,35,87,10],
	[20, 4,82,47,65],
	[19, 1,23,75, 3,34],
	[88, 2,77,73, 7,63,67],
	[99,65, 4,28, 6,16,70,92],
	[41,41,26,56,83,40,80,70,33],
	[41,48,72,33,47,32,37,16,94,29],
	[53,71,44,65,25,43,91,52,97,51,14],
	[70,11,33,28,77,73,17,78,39,68,17,57],
	[91,71,52,38,17,14,91,43,58,50,27,29,48],
	[63,66, 4,68,89,53,67,30,73,16,69,87,40,31],
	[ 4,62,98,27,23, 9,70,98,73,93,38,53,60, 4,23],
]


if __name__ == "__main__":
	print(compute())
