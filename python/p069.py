# 
# Solution to Project Euler problem 69
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions


def compute():
	totients = eulerlib.list_totients(10**6)
	ans = max(range(2, len(totients)), key=(lambda i: fractions.Fraction(i, totients[i])))
	return str(ans)


if __name__ == "__main__":
	print(compute())
