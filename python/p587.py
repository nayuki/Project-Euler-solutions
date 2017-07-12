# 
# Solution to Project Euler problem 587
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools, math


def compute():
	# The indefinite integral of (1 - sqrt(2x - x^2)) dx.
	def integral(x):
		t = x - 1.0
		return t - (math.sqrt(1.0 - t**2) * t + math.asin(t)) / 2.0
	
	lsectionarea = 1.0 - math.pi / 4.0
	for i in itertools.count(1):
		slope = 1.0 / i
		a = slope**2 + 1.0
		b = -2.0 * (slope + 1.0)
		c = 1.0
		x = (2.0 * c) / (-b + math.sqrt(b * b - 4 * a * c))
		concavetrianglearea = x * (1.0 - math.sqrt((-x + 2.0) * x)) / 2.0
		concavetrianglearea += integral(1.0) - integral(x)
		if concavetrianglearea / lsectionarea < 0.001:
			return str(i)


if __name__ == "__main__":
	print(compute())
