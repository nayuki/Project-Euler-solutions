# 
# Solution to Project Euler problem 106
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	SET_SIZE = 12
	
	def catalan(n):
		return eulerlib.binomial(n * 2, n) // (n + 1)
	
	ans = sum(eulerlib.binomial(SET_SIZE, i * 2) * (eulerlib.binomial(i * 2, i) // 2 - catalan(i))
		for i in range(2, SET_SIZE // 2 + 1))
	return str(ans)


if __name__ == "__main__":
	print(compute())
