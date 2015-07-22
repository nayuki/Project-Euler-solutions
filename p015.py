# 
# Solution to Project Euler problem 15
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	return str(binomial(40, 20))


def binomial(n, k):
	return eulerlib.factorial(n) // (eulerlib.factorial(k) * eulerlib.factorial(n - k))


if __name__ == "__main__":
	print(compute())
