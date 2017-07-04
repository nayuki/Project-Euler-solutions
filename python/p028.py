# 
# Solution to Project Euler problem 28
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# From the diagram, let's observe the four corners of an n * n square (where n is odd).
# It's not hard to convince yourself that the top right corner always has the value n^2.
# Working counterclockwise (backwards), the top left corner has the value n^2 - (n - 1),
# the bottom left corner has the value n^2 - 2(n - 1), and the bottom right is n^2 - 3(n - 1).
# Putting it all together, this outermost ring contributes 4n^2 - 6(n - 1) to the final sum.
# 
# Incidentally, the closed form of this sum is (4m^3 + 3m^2 + 8m - 9) / 6, where m = size.
def compute():
	SIZE = 1001  # Must be odd
	ans = 1  # Special case for size 1
	ans += sum(4 * i * i - 6 * (i - 1) for i in range(3, SIZE + 1, 2))
	return str(ans)


if __name__ == "__main__":
	print(compute())
