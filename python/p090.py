# 
# Solution to Project Euler problem 90
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	# Each die has (10 choose 6) arrangements, so we have at most 44100 arrangements to check
	ans = sum(1
		for i in range(1 << 10)
		for j in range(i, 1 << 10)  # Ensure i <= j to force the dice to be orderless
		# If both have Hamming weight of 6
		if eulerlib.popcount(i) == eulerlib.popcount(j) == 6 and is_arrangement_valid(i, j))
	return str(ans)


def is_arrangement_valid(a, b):
	if test_bit(a, 6) or test_bit(a, 9):
		a |= (1 << 6) | (1 << 9)
	if test_bit(b, 6) or test_bit(b, 9):
		b |= (1 << 6) | (1 << 9)
	return all(((test_bit(a, c) and test_bit(b, d)) or (test_bit(a, d) and test_bit(b, c)))
		for (c, d) in SQUARES)


def test_bit(x, i):
	return ((x >> i) & 1) != 0


SQUARES = [(i**2 // 10, i**2 % 10) for i in range(1, 10)]


if __name__ == "__main__":
	print(compute())
