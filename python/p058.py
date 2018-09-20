# 
# Solution to Project Euler problem 58
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions, itertools


# From the diagram, let's observe the four corners of an n * n square (where n is odd).
# It's not hard to convince yourself that:
# - The bottom right corner always has the value n^2.
# Working clockwise (backwards):
# - The bottom left corner has the value n^2 - (n - 1).
# - The top left corner has the value n^2 - 2(n - 1).
# - The top right has the value n^2 - 3(n - 1).
# Furthermore, the number of elements on the diagonal is 2n - 1.
def compute():
	TARGET = fractions.Fraction(1, 10)
	numprimes = 0
	for n in itertools.count(1, 2):
		for i in range(4):
			if eulerlib.is_prime(n * n - i * (n - 1)):
				numprimes += 1
		if n > 1 and fractions.Fraction(numprimes, n * 2 - 1) < TARGET:
			return str(n)


if __name__ == "__main__":
	print(compute())
