# 
# Solution to Project Euler problem 451
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import array, eulerlib, itertools


def compute():
	LIMIT = 20000000
	
	# Build table of smallest prime factors
	smallestprimefactor = array.array("L", itertools.repeat(0, LIMIT + 1))
	end = eulerlib.sqrt(len(smallestprimefactor) - 1)
	for i in range(2, len(smallestprimefactor)):
		if smallestprimefactor[i] == 0:
			smallestprimefactor[i] = i
			if i <= end:
				for j in range(i * i, len(smallestprimefactor), i):
					if smallestprimefactor[j] == 0:
						smallestprimefactor[j] = i
	
	
	# Returns all the solutions (in ascending order) such that
	# for each k, 1 <= k < n and k^2 = 1 mod n.
	def get_solutions(n):
		if smallestprimefactor[n] == n:  # n is prime
			return (1, n - 1)
		else:
			temp = []
			p = smallestprimefactor[n]
			sols = solutions[n // p]
			for i in range(0, n, n // p):
				for j in sols:
					k = i + j
					if k * k % n == 1:
						temp.append(k)
			return tuple(temp)
	
	
	# Process every integer in range
	solutions = [(), (), (1,)]
	ans = 0
	for i in range(3, LIMIT + 1):
		sols = get_solutions(i)
		if i <= LIMIT // 2:
			solutions.append(sols)
		ans += sols[-2]  # Second-largest solution
	return str(ans)


if __name__ == "__main__":
	print(compute())
