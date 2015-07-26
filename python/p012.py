# 
# Solution to Project Euler problem 12
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	triangular = 0
	for i in itertools.count(1):
		triangular += i
		if num_divisors(triangular) > 500:
			break
	return str(triangular)


def num_divisors(x):
	result = 0
	k = eulerlib.sqrt(x)
	for i in range(1, k + 1):
		if x % i == 0:
			result += 2
	if k**2 == x:
		result -= 1
	return result


if __name__ == "__main__":
	print(compute())
