# 
# Solution to Project Euler problem 2
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Computers are fast, so we can implement this solution directly without any clever math.
def compute():
	ans = 0
	x = 1  # Represents the current Fibonacci number being processed
	y = 2  # Represents the next Fibonacci number in the sequence
	while x <= 4000000:
		if x % 2 == 0:
			ans += x
		x, y = y, x + y
	return str(ans)


if __name__ == "__main__":
	print(compute())
