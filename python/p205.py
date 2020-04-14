# 
# Solution to Project Euler problem 205
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	nine_pyramidal_pdf = [1]
	PYRAMIDAL_DIE_PDF = [0, 1, 1, 1, 1]
	for i in range(9):
		nine_pyramidal_pdf = convolve(nine_pyramidal_pdf, PYRAMIDAL_DIE_PDF)
	
	six_cubic_pdf = [1]
	CUBIC_DIE_PDF = [0, 1, 1, 1, 1, 1, 1]
	for i in range(6):
		six_cubic_pdf = convolve(six_cubic_pdf, CUBIC_DIE_PDF)
	
	ans = 0
	for i in range(len(nine_pyramidal_pdf)):
		ans += nine_pyramidal_pdf[i] * sum(six_cubic_pdf[ : i])
	ans = float(ans) / (sum(nine_pyramidal_pdf) * sum(six_cubic_pdf))
	return f"{ans:.7f}"


def convolve(a, b):
	c = [0] * (len(a) + len(b) - 1)
	for i in range(len(a)):
		for j in range(len(b)):
			c[i + j] += a[i] * b[j]
	return c


if __name__ == "__main__":
	print(compute())
