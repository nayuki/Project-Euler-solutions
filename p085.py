# 
# Solution to Project Euler problem 85
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	TARGET = 2000000
	bestdiff = None
	end = eulerlib.sqrt(TARGET) + 1
	for w in range(1, end):
		for h in range(1, end):
			diff = abs(num_rectangles(w, h) - TARGET)
			if bestdiff is None or diff < bestdiff:
				ans = w * h
				bestdiff = diff
	return str(ans)


def num_rectangles(m, n):
	return (m + 1) * m * (n + 1) * n // 4  # A bit more than m^2 n^2 / 4


if __name__ == "__main__":
	print(compute())
