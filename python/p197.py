# 
# Solution to Project Euler problem 197
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math


def compute():
	def f(x):
		return math.floor(2.0 ** (30.403243784 - x * x)) / 1.0e9
	
	ITERATIONS = 10**12
	
	# Floyd's cycle-finding algorithm
	x = -1.0
	y = -1.0
	i = 0
	while i < ITERATIONS:
		# Here at the top of the loop, x = f^i(-1) and y = f^{2i}(-1)
		
		if i > 0 and x == y:  # This means index i is part of the cycle, and (2i - i) = i is some multiple of the true cycle length
			break
		
		# Advance the states at different speeds
		x = f(x)
		y = f(f(y))
		i += 1
	
	# Advance by many multiples of the cycle length, then deal with the remaining iterations
	remain = (ITERATIONS - i) % i
	for i in range(remain):
		x = f(x)
	
	ans = x + f(x)
	ans = math.floor(ans * 1.0e9) / 1.0e9  # Truncate to 9 digits after the decimal point
	return f"{ans:.9f}"


if __name__ == "__main__":
	print(compute())
