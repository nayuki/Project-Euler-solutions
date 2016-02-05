# 
# Solution to Project Euler problem 58
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# From the diagram, let's observe the four corners of an n * n square (where n is odd).
# It's not hard to convince yourself that:
# - The bottom right corner always has the value n^2.
# Working clockwise (backwards):
# - The bottom left corner has the value n^2 - (n - 1).
# - The top left corner has the value n^2 - 2(n - 1).
# - The top right has the value n^2 - 3(n - 1).
# Furthermore, the number of elements on the diagonal is 2n - 1.
def compute():
	numprimes = 0
	for i in itertools.count(1, 2):
		for j in range(4):
			if eulerlib.is_prime(i * i - j * (i - 1)):
				numprimes += 1
		if i > 1 and numprimes * 10 < i * 2 - 1:
			return str(i)


if __name__ == "__main__":
	print(compute())
