# 
# Solution to Project Euler problem 348
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


TARGET_WAYS = 4
TARGET_COUNT = 5

def compute():
	for i in itertools.count():
		limit = 10**i
		ans = try_search(limit)
		if ans is not None:
			return str(ans)


def try_search(limit):
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
