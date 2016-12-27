# 
# Solution to Project Euler problem 6
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Computers are fast, so we can implement this solution directly without any clever math.
# However for the mathematically inclined, there are closed-form formulas:
#   s  = N(N + 1) / 2.
#   s2 = N(N + 1)(2N + 1) / 6.
# Hence s^2 - s2 = (N^4 / 4) + (N^3 / 6) - (N^2 / 4) - (N / 6).
def compute():
	N = 100
	s = sum(i for i in range(1, N + 1))
	s2 = sum(i**2 for i in range(1, N + 1))
	return str(s**2 - s2)


if __name__ == "__main__":
	print(compute())
