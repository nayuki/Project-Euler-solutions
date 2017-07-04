# 
# Solution to Project Euler problem 303
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	# This function computes and returns the smallest positive multiple of n such that the result
	# uses only the digits 0, 1, 2 in base 10. For example, fmm(2) = 2, fmm(3) = 12, fmm(5) = 10.
	# 
	# As an overview, the algorithm has two phases:
	# 0. Determine whether a k-digit solution is possible, for increasing values of k.
	# 1. Knowing that a k-digit solution exists, construct the minimum solution.
	# 
	# Let n >= 1 be an arbitrary integer that will remain constant for the rest of the explanation.
	# 
	# When we look at the set of all k-digit numbers using only the digits {0, 1, 2}
	# (with possible leading zeros), each number will have a particular remainder modulo n.
	# For example, the set of 3-digit numbers is {000, 001, 002, 010, ..., 120, ..., 221, 222} (having 3^3 = 27 elements).
	# If one of these numbers is congruent to 0 mod n, then a solution to the original problem exists.
	# If not, then we prepend the digits 0, 1, 2 to all the numbers to get the set of all 4-digit numbers.
	# 
	# The size of the set of k-digit numbers grows exponentially with the length k, but we can avoid constructing and
	# working with the explicit set of numbers. Instead, we only need to keep track of whether each remainder modulo n has
	# a number that generates it or not. But we also need to exclude 0 as a solution, even though it is a multiple of n.
	# 
	# For 0-digit numbers, the only possible remainder is 0. All other remainders modulo n are impossible.
	# For 1-digit numbers, we look at all the possible 0-digit number remainders. If a remainder m is possible, then:
	# - By prepending the digit 0, a remainder of (m + 0*1 mod n) is possible for 1-digit numbers.
	# - By prepending the digit 1, a remainder of (m + 1*1 mod n) is possible for 1-digit numbers.
	# - By prepending the digit 2, a remainder of (m + 2*1 mod n) is possible for 1-digit numbers.
	# We keep iterating this process of tracking possible remainders for k-digit
	# numbers until the remainder of 0 mod n is possible in a non-zero number.
	# 
	# Now we know that a k-digit solution exists, such that the k-digit number consists of only {0, 1, 2},
	# and the number is congruent to 0 modulo n. To construct the minimum solution, we start at the most significant
	# digit of the result, choose the lowest possible value, and work backward toward the least significant digit.
	# 
	# The leading digit must be 1 or 2, because if it were 0 then it would contradict the fact that
	# no solution shorter than k digits exists. All subsequent digits can possibly be 0, 1, or 2.
	# 
	# At each value place, we choose the lowest digit value out of {0, 1, 2} such that there still
	# exists a solution for the remaining suffix of the number. When we choose a value at a certain
	# digit position, say 2 at the 8th place, we subtract 2 * 10^8 mod n from the ongoing remainder.
	def find_minimum_multiple(n):
		# feasible[i][j] indicates whether there exists an i-digit number that consists of
		# only the digits {0, 1, 2} (with possible leading zeros) having a remainder of j modulo n:
		# - 0: No i-digit number can form this remainder
		# - 1: Only zero can form this remainder
		# - 2: Some non-zero number can form this remainder
		
		# Initialization and base case
		feasible = [[1] + [0] * (n - 1)]
		
		# Add digits on the left side until a solution exists, using dynamic programming
		i = 0
		while feasible[i][0] != 2:  # Unbounded loop
			assert i == len(feasible) - 1
			prev = feasible[i]
			cur = list(prev)  # Clone
			digitmod = pow(10, i, n)
			for j in range(n):  # Run time of O(n)
				if prev[j] > 0:
					cur[(j + digitmod * 1) % n] = 2
					cur[(j + digitmod * 2) % n] = 2
			feasible.append(cur)
			i += 1
		
		# Construct the smallest solution using the memoized table
		# Run time of O(len(feasible)) bigint operations
		result = 0
		remainder = 0  # Modulo n
		# Pick digit values from left (most significant) to right
		for i in reversed(range(len(feasible) - 1)):
			digitmod = pow(10, i, n)
			# Leading digit must start searching at 1; subsequent digits start searching at 0
			for j in range((1 if (i == len(feasible) - 2) else 0), 3):
				newrem = (remainder - digitmod * j) % n
				if feasible[i][newrem] > 0:
					result = result * 10 + j
					remainder = newrem
					break
			else:
				raise AssertionError()
		return result
	
	ans = sum(find_minimum_multiple(n) // n for n in range(1, 10001))
	return str(ans)


if __name__ == "__main__":
	print(compute())
