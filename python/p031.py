# 
# Solution to Project Euler problem 31
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# We use the standard dynamic programming algorithm to solve the subset sum problem over integers.
# The order of the coin values does not matter, but the values need to be unique.
def compute():
	TOTAL = 200
	
	# At the start of each loop iteration, ways[i] is the number of ways to use {any copies
	# of the all the coin values seen before this iteration} to form an unordered sum of i
	ways = [1] + [0] * TOTAL
	for coin in [1, 2, 5, 10, 20, 50, 100, 200]:
		for i in range(len(ways) - coin):
			ways[i + coin] += ways[i]
	return str(ways[-1])


if __name__ == "__main__":
	print(compute())
