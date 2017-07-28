# 
# Solution to Project Euler problem 348
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# Given a range of integers [0, n), we can bulk-calculate how many ways
# each integer can be expressed as a sum of a cube and a square.
# 
# Start by initializing an array of length n with all zeros.
# Next, write a two-level loop to explore all the cubes i^3
# and squares j^2 such that their sum is less than n:
# 
#   ways = new int[n];  // Initially all zero
#   for (i = 2; i^3 < n; i++) {
#     for (j = 2; i^3 + j^2 < n; j++) {
#       ways[i^3 + j^2]++;
#     }
#   }
# 
# The array creation takes O(n) time. The outer loop runs O(n^(1/3)) iterations,
# the inner loop runs O(n^(1/2)) iterations per outer loop, hence the inner loop body
# runs O(n^(5/6)) iterations. Thus the whole process runs in O(n) time and memory.
# 
# Finally we iterate forward through the array, selecting numbers that
# are palindromes and can be summed in the target number of ways.
# 
# If the answer is not found in our range [0, n), then we increase the range and search
# again. If we multiply n by some factor (say 2 or 10), then the geometric series ensures
# that the total work we do is O(n) with respect to the magnitude of the final answer.

TARGET_WAYS = 4
TARGET_COUNT = 5

def compute():
	for i in itertools.count():
		limit = 10**i
		ans = try_search(limit)
		if ans is not None:
			return str(ans)


# Examines all integers in the range [0, limit), and returns the sum of the lowest
# TARGET_COUNT integers each with the property that it is a palidrome in base 10
# and it can be expressed in exactly TARGET_COUNT ways as a sum of a perfect square
# greater than 1 and a perfect cube greater than 1. If fewer than TARGET_COUNT integers
# in [0, limit) have the desired property, then None is returned. Note that if
# trySearch(n) == k != None, then for every m > n, trySearch(m) == k also holds.
def try_search(limit):
	# If i can be expressed as the sum of a square greater than 1 and
	# a cube greater than 1, then ways[i] is the number of different ways
	# it can be done. Otherwise, i is not a key in the ways dictionary.
	ways = {}
	
	for i in itertools.count(2):
		cube = i**3
		if cube >= limit:
			break
		for j in range(2, eulerlib.sqrt(limit - 1 - cube) + 1):
			index = cube + j**2
			ways[index] = ways.get(index, 0) + 1
	
	result = 0
	count = 0
	for i in sorted(ways.keys()):
		if ways[i] == TARGET_WAYS and is_palindrome(i):
			result += i
			count += 1
			if count == TARGET_COUNT:
				return result
	return None


def is_palindrome(x):
	s = str(x)
	return s == s[ : : -1]


if __name__ == "__main__":
	print(compute())
