# 
# Solution to Project Euler problem 85
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	TARGET = 2000000
	end = eulerlib.sqrt(TARGET) + 1
	gen = ((w, h) for w in range(1, end) for h in range(1, end))
	func = lambda wh: abs(num_rectangles(*wh) - TARGET)
	ans = min(gen, key=func)
	return str(ans[0] * ans[1])


def num_rectangles(m, n):
	return (m + 1) * m * (n + 1) * n // 4  # A bit more than m^2 n^2 / 4


if __name__ == "__main__":
	print(compute())
